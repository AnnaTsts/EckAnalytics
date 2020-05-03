package com.eck_analytics.FileSystem;

public enum FileType {
        STATISTIC_TXT("txt"),
        EXAMPLE_CSV("csv");

    private final String fileType;

    FileType(String fileType)
    {
        this.fileType = fileType;
    }

    public String getNameOfFileType() {
        return fileType;
    }
}
