package com.eck_analytics.FileSystem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InputFile {
    private FileType fileType;
    private String pathToFile;

    public InputFile(){}

    public InputFile(FileType fileType, String pathToFile){
        this.fileType = fileType;
        this.pathToFile = pathToFile;
    }

}
