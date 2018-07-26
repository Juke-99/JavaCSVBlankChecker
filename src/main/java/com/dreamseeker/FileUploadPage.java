package com.dreamseeker;

import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.wicket.request.mapper.parameter.PageParameters;;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.ajax.AjaxRequestTarget;

public class FileUploadPage extends WebPage {
  private static final long serialVersionUID = 1L;

  private FileUploadField fileUpload = new FileUploadField("fileUpload");;

  public FileUploadPage(final PageParameters params) {
    super(params);

    Form<?> form = new Form<Void>("form") {
      @Override protected void onSubmit() {
        super.onSubmit();

        FileUpload uploadedFile = fileUpload.getFileUpload();

        if(uploadedFile != null) {
          try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(uploadedFile.getInputStream()));
            String line = "";
            int count = 0;

            while((line = reader.readLine()) != null) {
              if(count != 0) {
                System.out.println(line);

                for(String i : line.split(",", -1)) {
                  if(i.isEmpty()) {
                    System.out.println("blank!");
                  } else {
                    System.out.println("index: " + i);
                  }
                }
              }

              count++;
            }
          } catch(IOException ioe) {
            ioe.printStackTrace();
          }
        }
			}
    };

    form.add(fileUpload);
    add(form);
  }
}
