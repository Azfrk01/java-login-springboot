package com.example.demo.dto;

import java.util.Map;

public class StatsResponse {

    private long totalStudents;
    private Map<String, Long> branchCounts;
    private Map<Integer, Long> yearCounts;

    public long getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(long totalStudents) {
        this.totalStudents = totalStudents;
    }

    public Map<String, Long> getBranchCounts() {
        return branchCounts;
    }

    public void setBranchCounts(Map<String, Long> branchCounts) {
        this.branchCounts = branchCounts;
    }

    public Map<Integer, Long> getYearCounts() {
        return yearCounts;
    }

    public void setYearCounts(Map<Integer, Long> yearCounts) {
        this.yearCounts = yearCounts;
    }
}