package com.PF.apirest.reportes;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
import com.PF.apirest.modelo.producto;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

public class productoExporterPDF {
	private List<producto> listaProductos;

	public productoExporterPDF(List<producto> listProductos) {
		super();
		this.listaProductos = listProductos;
	}

	private void escribirCabecera(PdfPTable tabla) {
		PdfPCell celda = new PdfPCell();
		celda.setBackgroundColor(Color.BLUE);
		celda.setPadding(5);

		Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
		fuente.setColor(Color.WHITE);

		celda.setPhrase(new Phrase("id", fuente));
		tabla.addCell(celda);

		celda.setPhrase(new Phrase("nombre", fuente));
		tabla.addCell(celda);

		celda.setPhrase(new Phrase("desccripcion", fuente));
		tabla.addCell(celda);

		celda.setPhrase(new Phrase("cantidad", fuente));
		tabla.addCell(celda);

		celda.setPhrase(new Phrase("precio", fuente));
		tabla.addCell(celda);
	}

	private void escribirDatos(PdfPTable tabla) {
		for (producto producto : listaProductos) {
			tabla.addCell(Integer.toString(producto.getId()));
			tabla.addCell(producto.getNombre());
			tabla.addCell(producto.getDescripcion());
			tabla.addCell(Integer.toString(producto.getCantidad()));
			tabla.addCell(Double.toString(producto.getPrecio()));
		}
	}

	public void exportar(HttpServletResponse response) throws DocumentException, IOException {
		Document documento = new Document(PageSize.A4);
		PdfWriter.getInstance(documento, response.getOutputStream());

		documento.open();

		Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		fuente.setColor(Color.BLUE);
		fuente.setSize(18);

		Paragraph titulo = new Paragraph("Lista de productos", fuente);
		titulo.setAlignment(Paragraph.ALIGN_CENTER);
		documento.add(titulo);

		PdfPTable tabla = new PdfPTable(5);
		tabla.setWidthPercentage(100);
		tabla.setSpacingBefore(15);
		tabla.setWidths(new float[] { 1.5f, 3.5f, 3.0f, 1.5f, 1.5f });
		tabla.setWidthPercentage(110);

		escribirCabecera(tabla);
		escribirDatos(tabla);

		documento.add(tabla);
		documento.close();

	}
}
