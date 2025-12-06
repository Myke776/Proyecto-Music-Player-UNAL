package musicfun.ui.component;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javafx.scene.Group;
import javafx.scene.shape.SVGPath;

public class GroupSVGPath extends Group {
	private String routeSVG;

	public GroupSVGPath(String routeSVG) {
		super();
		this.routeSVG = routeSVG;
		this.createSVGImage();
	}

	private void createSVGImage() {
		// Cargar el contenido SVG como string
		String svgContent = loadSVGContent(this.routeSVG);

		// Si no se pudo cargar el contenido, devolver SVG vacío
		if (svgContent == null || svgContent.isEmpty()) {
			System.err.println("No se pudo cargar el SVG: " + this.routeSVG);
		}else {
			// Extraer solo el path data del SVG
			String pathData = extractPathFromSVG(svgContent);
			// String pathData = svgContent;
	
			// Crear y configurar el SVGPath
			SVGPath svgPath = new SVGPath();
			svgPath.setContent(pathData);
			double scale = (double) 60/256;
			svgPath.setScaleX(scale);
			svgPath.setScaleY(scale);
			// Aplicar estilos básicos
			svgPath.setStyle("-fx-fill: white; -fx-stroke: transparent;");
			super.getChildren().add(svgPath);
		}

	}

	private String loadSVGContent(String resourcePath) {
		try {
			InputStream inputStream = getClass().getResourceAsStream(resourcePath);
			if (inputStream == null) {
				System.err.println("No se pudo encontrar el recurso: " + resourcePath);
				return null;
			}

			byte[] bytes = inputStream.readAllBytes();
			inputStream.close();
			return new String(bytes, StandardCharsets.UTF_8);
		} catch (Exception e) {
			System.err.println("Error leyendo el recurso: " + resourcePath + " - " + e.getMessage());
			return null;
		}
	}

	private String extractPathFromSVG(String svgContent) {
		// Si el contenido ya es un path directo (empieza con M o m)
		String trimmed = svgContent.trim();
		if (trimmed.startsWith("M") || trimmed.startsWith("m")) {
			return trimmed;
		}

		// Si es un SVG XML, buscar el atributo d
		int dIndex = svgContent.indexOf("d=\"");
		if (dIndex != -1) {
			int start = dIndex + 3;
			int end = svgContent.indexOf("\"", start);
			if (end != -1) {
				return svgContent.substring(start, end);
			}
		}

		// Buscar con comillas simples
		dIndex = svgContent.indexOf("d='");
		if (dIndex != -1) {
			int start = dIndex + 3;
			int end = svgContent.indexOf("'", start);
			if (end != -1) {
				return svgContent.substring(start, end);
			}
		}

		// Si no se encuentra, devolver el contenido completo
		return trimmed;
	}
}
