package com.owdienko.jaroslaw.taskfromnewwork.Model;

/**
 * Created by Iaroslav Ovdienko on 19.07.17.
 * - jaroslaw - 2017 -
 */

public class DataEntity {
    private String[] log;
    private String[] general;
    private String[] docs;
    private String[] dvir;

    public DataEntity() {
        log = new String[]{"Log", "Log", "Log", "Log", "Log", "Log"};
        general = new String[]{"General", "General", "General", "General", "General", "General"};
        docs = new String[]{"Docs", "Docs", "Docs", "Docs", "Docs", "Docs"};
        dvir = new String[]{"DVIR", "DVIR", "DVIR", "DVIR", "DVIR", "DVIR"};
    }

    public String[] getLog() {
        return log;
    }

    public String[] getGeneral() {
        return general;
    }

    public String[] getDocs() {
        return docs;
    }

    public String[] getDvir() {
        return dvir;
    }
}
