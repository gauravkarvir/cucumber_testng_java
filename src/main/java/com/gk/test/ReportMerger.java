package com.gk.test;


import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

class ReportMerger {
    private static final Logger LOG = LoggerFactory.getLogger(ReportMerger.class);
    private static String reportImageExtension = "png";

    public static void main(String[] args) throws Throwable {
        File reportDirectory = new File(args[0]);
        if (reportDirectory.exists()) {
            ReportMerger munger = new ReportMerger();
            munger.mergeReports(reportDirectory);
        }
    }

    /**
     * Merge all reports together into master report in given reportDirectory
     *
     * @param reportDirectory report directory
     * @throws Exception
     */
    private void mergeReports(File reportDirectory) throws IOException {
        //merging js reports for parallel tests
        mergeResponse(reportDirectory, "js", "report.js");
        //merging json response for parallel tests
        mergeResponse(reportDirectory, "json", "cucumber.json");
        //Writing failed scenarios to text file
        getListOfFailedScenarios(reportDirectory);
    }

    private void mergeResponse(File reportDirectory, String format, String reportFileName) throws IOException {
        LOG.info("In ReportMerger--->Report directory: " + reportDirectory + "  File format: " + format + "  Report fileName: " + reportFileName);
        Collection<File> existingReports = FileUtils.listFiles(reportDirectory, new String[]{format}, true);
        File mergedReport = null;

        for (File report : existingReports) {
            //only address report files
            if (report.getName().equals(reportFileName)) {
                //rename all the image files (to give unique names) in report directory and update report
                renameEmbeddedImages(report);
                //if we are on the first pass, copy the directory of the file to use as basis for merge
                if (mergedReport == null) {
                    if ("json".equals(format)) {
                        FileUtils.copyFileToDirectory(report.getAbsoluteFile(), reportDirectory);
                        mergedReport = new File(reportDirectory, reportFileName);
                    } else if ("js".equals(format)) {
                        FileUtils.copyDirectory(report.getParentFile(), reportDirectory);
                        mergedReport = new File(reportDirectory, reportFileName);

                        //Remove json file from parent folder
                        File jsonFile = new File(reportDirectory + "/cucumber.json");
                        if (jsonFile.exists()) {
                            FileUtils.forceDelete(jsonFile);
                        }
                    }
                } else {
                    //otherwise merge this report into existing master report
                    if ("js".equals(format)) {
                        mergeJsFiles(mergedReport, report);
                    } else if ("json".equals(format)) {
                        mergeJsonFiles(mergedReport, report);
                    }
                }
            }
        }
    }

    /**
     * To get failed scenarios and write it to rerun file in rerun folder
     *
     * @param reportDirectory reportDirectory
     * @throws Exception
     */
    private void getListOfFailedScenarios(File reportDirectory) throws IOException {
        String rerunReportFileName = "rerun.txt";
        String rerunReportDirectory = "rerun/";
        Set<String> rerunFailedScenariosList = new HashSet();
        StringBuilder failedScenariosString = new StringBuilder();

        Collection<File> rerunSourceReports = FileUtils.listFiles(reportDirectory, new String[]{"txt"}, true);
        File mergedRerunReport = new File(rerunReportDirectory, rerunReportFileName);

        for (File rerunSourceReport : rerunSourceReports) {
            rerunFailedScenariosList.add(FileUtils.readFileToString(rerunSourceReport));
        }
        for (String failedScenariosListToString : rerunFailedScenariosList) {
            failedScenariosString.append(failedScenariosListToString).append(" ");
        }
        String failedScenarios = failedScenariosString.toString().trim();
        FileUtils.writeStringToFile(mergedRerunReport, failedScenarios);
    }

    /**
     * merge source file into target
     *
     * @param target target location
     * @param source source location
     */
    private void mergeJsFiles(File target, File source) throws IOException {
        //copy embedded images
        Collection<File> embeddedImages = FileUtils.listFiles(source.getParentFile(), new String[]{reportImageExtension}, true);
        for (File image : embeddedImages) {
            FileUtils.copyFileToDirectory(image, target.getParentFile());
        }
        //merge report files
        String targetReport = FileUtils.readFileToString(target);
        String sourceReport = FileUtils.readFileToString(source);
        FileUtils.writeStringToFile(target, targetReport + sourceReport);
    }


    /**
     * merge source file into target
     *
     * @param jsonTarget target location
     * @param jsonSource source location
     */
    private void mergeJsonFiles(File jsonTarget, File jsonSource) throws IOException {
        //copy embedded images
        Collection<File> embeddedImages = FileUtils.listFiles(jsonSource.getParentFile(), new String[]{reportImageExtension}, true);
        for (File image : embeddedImages) {
            FileUtils.copyFileToDirectory(image, jsonTarget.getParentFile());
        }
        //merge report files
        String targetReport = FileUtils.readFileToString(jsonTarget);
        String sourceReport = FileUtils.readFileToString(jsonSource);
        FileUtils.writeStringToFile(jsonTarget, targetReport.substring(0, targetReport.length() - 2) + "," + sourceReport.substring(1));
    }

    /**
     * Give unique names to embedded images to ensure they aren't lost during merge
     * Update report file to reflect new image names
     *
     * @param reportFile reportFile
     */
    private void renameEmbeddedImages(File reportFile) throws IOException {
        File reportDirectory = reportFile.getParentFile();
        Collection<File> embeddedImages = FileUtils.listFiles(reportDirectory, new String[]{reportImageExtension}, true);
        String fileAsString = FileUtils.readFileToString(reportFile);
        for (File image : embeddedImages) {
            String curImageName = image.getName();
            String uniqueImageName = UUID.randomUUID().toString() + "." + reportImageExtension;
            boolean imageRenameFlag = image.renameTo(new File(reportDirectory, uniqueImageName));
            if (imageRenameFlag) {
                fileAsString = fileAsString.replace(curImageName, uniqueImageName);
                FileUtils.writeStringToFile(reportFile, fileAsString);
            }
        }
    }
}