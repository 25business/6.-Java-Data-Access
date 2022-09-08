package com.example;


import com.example.templating.Renderer;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import io.javalin.Javalin;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.HashMap;

public class Program {
    public static void main(String[] args) {
        try {
            Javalin app = Javalin.create().start(7070);
            app.get("/", ctx -> {
                ctx.html(Renderer.render("order_form.html"));
            });
            app.post("/order", ctx -> {
                HashMap<String, Object> modelData = new HashMap<>();
                modelData.put("size", ctx.formParam("size"));
                modelData.put("meat", ctx.formParams("meat"));
                modelData.put("toppings", ctx.formParams("toppings"));
                modelData.put("contact_info", ctx.formParam("contact_info"));

                String html_content = Renderer.render("order_form.ftl", modelData);

                ByteArrayOutputStream os = new ByteArrayOutputStream();
                PdfRendererBuilder builder = new PdfRendererBuilder();
                builder.useFastMode().withHtmlContent(html_content,"").toStream(os).run();

                ctx.contentType("application/pdf");
                ctx.header("content-disposition","attachment; filename=order.pdf");
                ctx.result(os.toByteArray());
            });

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
