package com.eck_analytics.Services;

import com.eck_analytics.FileSystem.FileType;
import com.eck_analytics.FileSystem.InputFile;
import com.eck_analytics.FileSystem.SimpleFileReader;
import com.eck_analytics.Model.Anomaly;
import com.eck_analytics.Model.Example;
import com.eck_analytics.Model.Result;
import com.eck_analytics.Utils.Alphabet;
import com.eck_analytics.Utils.LinguisticChainBuilder;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public interface LinguisticChainService {
    void getExamplesFromFiles(String fileCSVPath, String fileTxtPath);
    void saveChainIntoFile(String fileCSVPath, String fileTxtPath,String fileName) throws IOException;
}


