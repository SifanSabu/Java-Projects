package org.medtrak;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.commons.logging.Log;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Logger;

public class Main {

    // private final PDFGenerator pdfGen;  <-- This is how to get class reference within another class
    // ^put in dtf gen class

    private final Font font = FontFactory.getFont(FontFactory.HELVETICA, 9, BaseColor.BLACK);
    private final Font fontBold = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 9, BaseColor.BLACK);
    private final Font fontItalics = FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 9, BaseColor.BLACK) ;
    private final Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, BaseColor.BLACK);
    private final Font subFont = FontFactory.getFont(FontFactory.HELVETICA, 8, BaseColor.BLACK);

    private int pageTotal;

    public static void main(String[] args) {
        Main pdfGenerator = new Main();
        int totalDevices = 9; // Total number of medical procedures

        // Create a new Document and set margins
        Document document = new Document(PageSize.A4);
        document.setMargins(document.leftMargin(), document.rightMargin(), 30, 30);

        try {
            // Create a new PdfWriter
            PdfWriter.getInstance(document, new FileOutputStream("procedure_name+date.pdf"));

            // Open the Document
            document.open();
            document.addTitle("Medical Device Tracking Form");

            // Create Date, Title, and Manufacturer Header
            PdfPTable headerTable = pdfGenerator.createHeaderTable();
            document.add(headerTable);
//            document.add(new Paragraph("\n")); // Add some spacing between tables

            // Create Patient Info Fields
            PdfPTable patientInfoTable = pdfGenerator.createPatientInfoTable();
            document.add(patientInfoTable);
//            document.add(new Paragraph("\n"));

            // Create Patient Info Fields
            PdfPTable institutionInfoTable = pdfGenerator.createInstitutionInfoTable();
            document.add(institutionInfoTable);
//            document.add(new Paragraph("\n"));

            // Create Patient Info Fields
            PdfPTable physicianInfoTable = pdfGenerator.createPhysicianInfoTable();
            document.add(physicianInfoTable);
//            document.add(new Paragraph("\n"));

            // Create Patient Info Fields
            PdfPTable manufacturerInfoTable = pdfGenerator.createManufacturerInfoTable();
            document.add(manufacturerInfoTable);
            document.add(new Paragraph("\n"));

            // Generate tables for medical procedures
            for (int i = 1; i <= totalDevices; i++) {
                PdfPTable devicesTable = pdfGenerator.createDeviceTable(i);
                document.add(devicesTable);
                document.add(new Paragraph("\n"));
            }

            PdfPTable footerTable = new PdfPTable(pdfGenerator.addFooterTable());
            document.add(footerTable);

//            document.addHeader("Page Count", document.getPageNumber()+  "");
            // Close the Document
            document.close();

            System.out.println("Medical DTF PDF created successfully.");
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private PdfPTable createHeaderTable() throws DocumentException {
        PdfPTable headerTable = new PdfPTable(10);
        headerTable.setWidthPercentage(100);

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String formattedDate = dateFormat.format(new Date());

        // Date cell
        PdfPCell dateCell = new PdfPCell(new Paragraph("*Cardiac 2 Resynchronization Therapy Pacemaker*\n*Procedure Date*", fontItalics));
        dateCell.setColspan(3);
        dateCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        dateCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        headerTable.addCell(dateCell);

        // Title cell
        Paragraph title =  new Paragraph("MEDICAL DEVICE TRACKING FORM\n", fontTitle);
        Paragraph titleSubText = new Paragraph("Auto Generated via MedTrak on " + formattedDate, subFont);
        title.add(titleSubText);
        PdfPCell titleCell = new PdfPCell(title);
        titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        titleCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        titleCell.setColspan(4);
        headerTable.addCell(titleCell);

        // Manufacturer cell
        PdfPCell mftrCell = new PdfPCell(new Paragraph("BOSTON SCIENTIFIC CORPORATION", fontItalics));
        mftrCell.setColspan(3);
        mftrCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        mftrCell.setVerticalAlignment((Element.ALIGN_MIDDLE));
        headerTable.addCell(mftrCell);

        // Body cell
        PdfPCell bodyCell = new PdfPCell(new Paragraph("The Code of Federal Regulations (21 CFR 821) requires that " +
                "final distributors (i.e. physicians and hospitals) of tracked devices provide the information below to " +
                "the device manufacturer.\n\n", subFont));
        bodyCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        bodyCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        bodyCell.setColspan(10);
        bodyCell.setBorder(PdfPCell.NO_BORDER);
        headerTable.addCell(bodyCell);

        return headerTable;
    }

    private PdfPTable createPatientInfoTable() {
        PdfPTable patientInfoTable = new PdfPTable(18);
        patientInfoTable.setWidthPercentage(105);

        String firstName = "First Name:";
        String lastname = "Last Name:";
        String medTrakID = "MedTrak Id:";
        String dateOfBirth = "Date of Birth (MM/dd/yyyy):";
        String phoneNumber = "Phone Number:";
        String addressLine1 = "Address Line 1:";
        String addressLine2 = "Address Line 2:";
        String city = "City, ";
        String state = "State ";
        String zipCode = "Zip:";

        // Patient Information Header
        createTableHeader(patientInfoTable, "A. Patient Information", 18, 18F, 0.7F, fontBold);
        // Last Name cell pair (label/value)
        createCellPair(patientInfoTable, lastname, "", 2, 15F, font);
        // First Name cell pair (label/value)
        createCellPair(patientInfoTable, firstName, "", 2, 15F, font);
        // MedTrak ID cell pair (label/value)
        createCellPair(patientInfoTable, medTrakID, "", 2, 15F, font);
        // Date of Birth cell pair (label/value)
        createCellPair(patientInfoTable, dateOfBirth, "", 2, 15F, font);
        // Address Line 1 cell pair (label/value)
        createCellPair(patientInfoTable, addressLine1, "", 2, 15F, font);
        // Phone Number cell pair (label/value)
        createCellPair(patientInfoTable, phoneNumber, "", 2, 15F, font);
        // Address Line 2 cell pair (label/value)
        createCellPair(patientInfoTable, addressLine2, "", 2, 15F, font);
        // City/State/Zip cell pair (label/value)
        createCellPair(patientInfoTable, city + state + zipCode, "", 2, 15F, font);

        return patientInfoTable;
    }

    private PdfPTable createInstitutionInfoTable() {
        PdfPTable institutionInfoTable = new PdfPTable(18);
        institutionInfoTable.setWidthPercentage(105);

        String institutionName = "Institution Name:";
        String orNurse = "OR Nurse:";
        String phoneNumber = "Phone Number:";
        String addressLine1 = "Address Line 1:";
        String addressLine2 = "Address Line 2:";
        String city = "City,";
        String state = "State ";
        String zipCode = "Zip:";

        // Institution Information Header
        createTableHeader(institutionInfoTable, "B. Institution Information", 18, 18F, 0.7F, fontBold);
        // Institution Name cell pair (label/value)
        createCellPair(institutionInfoTable, institutionName, "", 2, 15F, font);
        // Phone Number cell pair (label/value)
        createCellPair(institutionInfoTable, phoneNumber, "", 2, 15F, font);
        // Address Line 1 cell pair (label/value)
        createCellPair(institutionInfoTable, addressLine1, "", 2, 15F, font);
        // OR Nurse cell pair (label/value)
        createCellPair(institutionInfoTable, orNurse, "", 2, 15F, font);
        // Address Line 2 cell pair (label/value)
        createCellPair(institutionInfoTable, addressLine2, "", 2, 15F, font);
        // City/State/Zip cell pair (label/value)
        createCellPair(institutionInfoTable, city + state + zipCode, "", 2, 15F, font);

        return institutionInfoTable;
    }

    private PdfPTable createPhysicianInfoTable() {
        PdfPTable physicianInfoTable = new PdfPTable(18);
        physicianInfoTable.setWidthPercentage(105);

        String physicianName = "Physician Name:";
        String phoneNumber = "Phone Number:";
        String physicianEmail = "Physician Email:";
        String addressLine1 = "Address Line 1:";
        String addressLine2 = "Address Line 2:";
        String city = "City,";
        String state = "State ";
        String zipCode = "Zip:";

        // Physician Information Header
        createTableHeader(physicianInfoTable, "C. Physician Information", 18, 18F, 0.7F, fontBold);
        // Physician Name cell pair (label/value)
        createCellPair(physicianInfoTable, physicianName, "", 2, 15F, font);
        // Phone Number cell pair (label/value)
        createCellPair(physicianInfoTable, phoneNumber, "", 2, 15F, font);
        // Address Line 1 cell pair (label/value)
        createCellPair(physicianInfoTable, addressLine1, "", 2, 15F, font);
        // Physician Email cell pair (label/value)
        createCellPair(physicianInfoTable, physicianEmail, "", 2, 15F, font);
        // Address Line 2 cell pair (label/value)
        createCellPair(physicianInfoTable, addressLine2, "", 2, 15F, font);
        // City/State/Zip cell pair (label/value)
        createCellPair(physicianInfoTable, city + state + zipCode, "", 2, 15F, font);

        return physicianInfoTable;
    }

    private PdfPTable createManufacturerInfoTable() {
        PdfPTable manufacturerInfoTable = new PdfPTable(18);
        manufacturerInfoTable.setWidthPercentage(105);

//        String manufacturerName = "Physician Name:";
//        String phoneNumber = "Phone Number:";
//        String physicianEmail = "Physician Email:";
//        String addressLine1 = "Address Line 1:";
//        String addressLine2 = "Address Line 2:";
//        String city = "City,";
//        String state = "State ";
//        String zipCode = "Zip:";

        // Physician Information Header
        createTableHeader(manufacturerInfoTable, "D. Manufacturer Information", 18, 18F, 0.7F, fontBold);
        // Procedure description cell
        createCellPair(manufacturerInfoTable, "Manufacturer Name:", "BOSTON SCIENTIFIC CORPORATION", 2 , 15F, font);
        // Phone Number cell pair (label/value)
        createCellPair(manufacturerInfoTable, "Phone Number:", "5125788350", 2, 15F, font);
        // Address Line 1 cell pair (label/value)
        createCellPair(manufacturerInfoTable, "Address Line 1:", "", 2, 15F, font);
        // OR Nurse cell pair (label/value)
        createCellPair(manufacturerInfoTable, "Manufacturer Email:", "", 2, 15F, font);
        // Address Line 2 cell pair (label/value)
        createCellPair(manufacturerInfoTable, "Address Line 2:", "", 2, 15F, font);
        // City/State/Zip cell pair (label/value)
        createCellPair(manufacturerInfoTable, "City, State Zip:", "Round Rock, TX 78681", 2, 15F, font);

        return manufacturerInfoTable;
    }

    // Create a table for a specific medical device
    private PdfPTable createDeviceTable(int deviceNumber) {
        PdfPTable deviceTable = new PdfPTable(18);
        deviceTable.setWidthPercentage(105);

//        String deviceName = "Device Name";
//        String procedureDescription = "Description of Procedure:";
//        String physicianName = "Physician:\n";
//        String physicianId = "Id:";
//        String deviceNam = "Device:";
//        String deviceId = "Id:";
//        String manufacturerName = "Manufacturer:";
//        String manufacturerId = "Id:";

        // Device Name cell
        createTableHeader(deviceTable, "[" + deviceNumber + "] Endovascular graft system, aortic aneurysm treatment", 20, 20F, 0.7F , fontBold);
        // UDI cell
        createCellPair(deviceTable, "UDI:", "00763000078621", 3, 15F, font);
        // Serial Number cell
        createCellPair(deviceTable, "Serial Number:", "45t876y5", 3, 15F, font);
        // Lot Number cell
        createCellPair(deviceTable, "Lot Number:", "54554", 3, 15F, font);
        // Manufacture Date cell
        createCellPair(deviceTable, "Manufacture Date:", "05/12/2023", 3, 15F, font);
        // Expiration Date cell
        createCellPair(deviceTable, "Expiration Date:", "5/12/2023", 3, 15F, font);
        // Explant/Implant cell
        createCellPair(deviceTable, "Explant/Implant:", "Explant", 3, 15F, font);

        return deviceTable;
    }

    private PdfPTable addFooterTable() {
        PdfPTable footerTable = new PdfPTable(1);
        footerTable.setWidthPercentage(105);

        createCell(footerTable, "Records and other information submitted to MedTrak and FDA shall be protected from " +
                "public disclosure. \nInformation contained in such records that would identify patient or research subjects " +
                "shall not be available for public disclosure.",1, 20F, false, subFont);
        return footerTable;
    }

    private PdfPCell createCell(PdfPTable targetTable, String cellValue, int cellWidth, float cellHeight, boolean border, Font font) {
        PdfPCell cellName = new PdfPCell(new Paragraph(cellValue, font));
        cellName.setColspan(cellWidth);
        cellName.setFixedHeight(cellHeight);
        if(!border) {cellName.setBorder(PdfPCell.NO_BORDER);}
        cellName.setHorizontalAlignment(Element.ALIGN_CENTER);
        targetTable.addCell(cellName);
        return cellName;
    }
    private PdfPCell createCellPair(PdfPTable targetTable, String cellValue, String cellInput,int colCount, float cellHeight, Font font) {
        PdfPCell cellName = new PdfPCell(new Paragraph(cellValue, font));
        PdfPCell cellInputField = new PdfPCell(new Paragraph(cellInput, font));
        cellName.setFixedHeight(cellHeight);
        if(colCount % 2 == 0) {
            cellName.setColspan(3);
            cellInputField.setColspan(6);
        } else {
            cellName.setColspan(3);
            cellInputField.setColspan(3);
        }
        cellName.setHorizontalAlignment(Element.ALIGN_LEFT);
        cellName.setGrayFill(0.95F);
        targetTable.addCell(cellName);
        targetTable.addCell(cellInputField);
        return cellName;
    }
    private void createTableHeader(PdfPTable targetTable, String cellValue, int cellWidth, float cellHeight, float grayFill, Font font) {
        PdfPCell cellName = new PdfPCell(new Paragraph(cellValue, font));
        cellName.setColspan(cellWidth);
        cellName.setFixedHeight(cellHeight);
        cellName.setGrayFill(grayFill);
        cellName.setHorizontalAlignment(Element.ALIGN_LEFT);
        targetTable.addCell(cellName);
    }

}
