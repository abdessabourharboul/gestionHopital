/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 *
 * @author moulaYounes
 */
public class JasperUtil {



    public static void generatePdf(List list, String cheminJapser, String cheminExport, Map params, boolean isPdfVisible) throws FileNotFoundException, JRException, IOException {
        InputStream reportSource = null;
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(list);
        reportSource = new FileInputStream(cheminJapser);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reportSource, params, beanCollectionDataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, cheminExport);
        if (isPdfVisible) {
            showPdf(cheminExport);
        }
          reportSource.close();
    }

    public static void showPdf(String chemin) throws IOException {
        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + chemin);
    }
}
