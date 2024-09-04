package com.azacloud.fileserver.service;

public class FileSummary {
    private int totalFiles;
    private long totalSize;

    // Constructor
    public FileSummary(int totalFiles, long totalSize) {
        this.totalFiles = totalFiles;
        this.totalSize = totalSize;
    }

    // Getters and Setters
    public int getTotalFiles() {
        return totalFiles;
    }

    public void setTotalFiles(int totalFiles) {
        this.totalFiles = totalFiles;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

    // Optionally, override toString for better logging/debugging
    @Override
    public String toString() {
        return "FileSummary{" +
                "totalFiles=" + totalFiles +
                ", totalSize=" + totalSize +
                '}';
    }
}
