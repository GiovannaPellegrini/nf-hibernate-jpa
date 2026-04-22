package br.digithink.nf.util;

import java.util.Objects;

public class DigithinkUtil {

    public static boolean ehVazio(String str){
        return Objects.isNull(str) || str.isEmpty();
    }
    
}
