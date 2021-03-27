package com.example.worddef_fragment.fragment.processes.explorer;

public class FragmentExplorerFactory {
    public FragmentExplorer create(String type) {
        FragmentExplorer result;

        switch (type.toLowerCase()) {
            case "wordset":
                result=new WordSetExplorer();
                break;
            case "worddef":
                result=new WordDefExplorer();
                break;
            default: result=null;
        }
        return  result;
    }
}
