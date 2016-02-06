package com.codefork.refine.viaf;

import org.junit.Test;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class VIAFParserTest {

    private static String joinStrings(List<String> strings, String delimiter) {
        StringBuilder b = new StringBuilder();
        for(String s : strings) {
            if(b.length() > 0) {
                b.append(delimiter);
            }
            b.append(s);
        }
        return b.toString();
    }

    @Test
    public void testParseNames() throws Exception {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser parser = spf.newSAXParser();
        VIAFParser viafParser = new VIAFParser();

        InputStream is = getClass().getResourceAsStream("/steinbeck_no_type.xml");
        parser.parse(is, viafParser);

        List<VIAFResult> results = viafParser.getResults();

        VIAFResult firstResult = results.get(0);
        VIAFResult secondResult = results.get(1);

        assertEquals(10, firstResult.getNameEntries().size());

        assertEquals("Steinbeck, John, 1902-1968",
                firstResult.getNameEntries().get(0).getName());
        assertEquals("LC,BIBSYS,BNF,KRNLK,N6I,LAC,BNE,SUDOC,BAV,BNC,NLI,B2Q,PTBNP,NLP,LNB,SELIBR,NLA,ICCU,NDL,DNB,NUKAT,NKC",
                joinStrings(firstResult.getNameEntries().get(0).getSources(), ","));

        assertEquals("Steinbeck, John (John Ernst), 1902-1968",
                firstResult.getNameEntries().get(1).getName());
        assertEquals("NTA",
                joinStrings(firstResult.getNameEntries().get(1).getSources(), ","));

        assertEquals("NSK,SWNL",
                joinStrings(firstResult.getNameEntries().get(2).getSources(), ","));
        assertEquals("WKP",
                joinStrings(firstResult.getNameEntries().get(3).getSources(), ","));
        assertEquals("LNL,EGAXA",
                joinStrings(firstResult.getNameEntries().get(4).getSources(), ","));
        assertEquals("NLI",
                joinStrings(firstResult.getNameEntries().get(5).getSources(), ","));
        assertEquals("NLI",
                joinStrings(firstResult.getNameEntries().get(6).getSources(), ","));
        assertEquals("NLI",
                joinStrings(firstResult.getNameEntries().get(7).getSources(), ","));
        assertEquals("NLR",
                joinStrings(firstResult.getNameEntries().get(8).getSources(), ","));
        assertEquals("JPG",
                joinStrings(firstResult.getNameEntries().get(9).getSources(), ","));

        assertEquals(5, secondResult.getNameEntries().size());

        assertEquals("Steinbeck, John 1946-1991",
                secondResult.getNameEntries().get(0).getName());
        assertEquals("NLP,ICCU,DNB,BNF",
                joinStrings(secondResult.getNameEntries().get(0).getSources(), ","));

    }

}
