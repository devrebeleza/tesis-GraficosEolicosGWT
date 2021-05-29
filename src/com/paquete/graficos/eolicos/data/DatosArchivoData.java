package com.paquete.graficos.eolicos.data;

public class DatosArchivoData {

  private static DatosArchivoRecord[] records;

  public static DatosArchivoRecord[] getRecords() {
  if (records == null) {
    records = getNewRecords();
  }
  return records;
  }

  public static DatosArchivoRecord[] getNewRecords() {
  return new DatosArchivoRecord[]{
    new DatosArchivoRecord("account", "ABCD Pty Ltd", "(02) 251 6641", "Sydney", 
              "Sean Doyle", "sales@uptick.com.au"),
    new DatosArchivoRecord("account", "DEF Pty Ltd", "(02) 251 6642", "Melbourne",
              "Mario Bernatovic", "sales@uptick.com.au"),
    new DatosArchivoRecord("account", "GHI Pty Ltd", "(02) 251 6643", "Newcastle",
              "Rob Ferguson", "sales@uptick.com.au"),
    new DatosArchivoRecord("account", "JKL Pty Ltd", "(02) 251 6644", "Perth",
              "Alister Bennett", "sales@uptick.com.au"),
    new DatosArchivoRecord("account", "MNO Pty Ltd", "(02) 251 6645", "Newcastle",
              "Mark Kirkpatrick", "sales@uptick.com.au"),
    new DatosArchivoRecord("account", "PQR Pty Ltd", "(02) 251 6647", "Bankstown",
              "Grahame King", "sales@uptick.com.au"),
    new DatosArchivoRecord("account", "STU Pty Ltd", "(02) 251 6648", "Sydney",
              "Peter Wood", "sales@uptick.com.au"),
    new DatosArchivoRecord("account", "VWX Pty Ltd", "(02) 251 6649", "Newcastle",
              "Ross Hodge", "sales@uptick.com.au"),
    new DatosArchivoRecord("account", "YZA Pty Ltd", "(02) 251 6610", "Sydney",
              "Darren Poyner", "sales@uptick.com.au"),
    new DatosArchivoRecord("account", "123 Pty Ltd", "(02) 251 6611", "Glebe",
              "Carl Blick", "sales@uptick.com.au"),
    new DatosArchivoRecord("account", "456 Pty Ltd", "(02) 251 6612", "Sydney",
              "Mark Powrie", "sales@uptick.com.au"),
    new DatosArchivoRecord("account", "789 Pty Ltd", "(02) 251 6613", "Perth",
              "Jason Bance", "sales@uptick.com.au"),
    new DatosArchivoRecord("account", "101 Pty Ltd", "(02) 251 6614", "Newcastle",
              "Patrick Keegan", "sales@uptick.com.au")
  };
 }
}