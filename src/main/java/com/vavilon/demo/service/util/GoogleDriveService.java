package com.vavilon.demo.service.util;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.vavilon.demo.util.ContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class GoogleDriveService {
    private Drive drive;
    private final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private final File CREDENTIALS_FOLDER = new File("credentials");

    private final String CLIENT_SECRET_FILE_NAME = "client_secret.json";
    private final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE);
    private final ContextHolder contextHolder;

    public GoogleDriveService(final ContextHolder contextHolder) {
        this.contextHolder = contextHolder;
        try {
            final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            drive = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT)).build();
        } catch (final Exception e) {
            log.error("Error during trying to create GoogleDriveService", e);
        }
    }

    private Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws Exception {
        final File clientSecretFilePath = new File(CREDENTIALS_FOLDER, CLIENT_SECRET_FILE_NAME);
        try (final InputStream in = new FileInputStream(clientSecretFilePath)) {
            final GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,
                    GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in)), SCOPES).setDataStoreFactory(new FileDataStoreFactory(CREDENTIALS_FOLDER))
                    .setAccessType("offline").build();
            return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
        } catch (IOException e) {
            log.error("File not found exception", e);
            throw new Exception();
        }
    }

    public String uploadFile(final MultipartFile file, final String fileName) throws Exception {
        try (final InputStream inputStream = file.getInputStream()) {
            final com.google.api.services.drive.model.File fileMetaData = new com.google.api.services.drive.model.File();
            fileMetaData.setName(fileName);
            fileMetaData.setParents(Collections.singletonList(contextHolder.getFolderProductImages()));
            final com.google.api.services.drive.model.File result = drive.files().create(fileMetaData,
                    new InputStreamContent(Files.probeContentType(new File(fileName).toPath()), inputStream)).execute();
            log.info("Successfully upload file " + fileName + " to Google Drive");
            return result.getId();
        }
    }
    public String uploadProfileImage(final MultipartFile file, final String fileName) throws Exception {
        try (final InputStream inputStream = file.getInputStream()) {
            final com.google.api.services.drive.model.File fileMetaData = new com.google.api.services.drive.model.File();
            fileMetaData.setName(fileName);
            fileMetaData.setParents(Collections.singletonList(contextHolder.getFolderProfileImages()));
            final com.google.api.services.drive.model.File result = drive.files().create(fileMetaData,
                    new InputStreamContent(Files.probeContentType(new File(fileName).toPath()), inputStream)).execute();
            log.info("Successfully upload file " + fileName + " to Google Drive");
            return result.getId();
        }
    }
    public void deleteFile(final String fileId) throws Exception {
        drive.files().delete(fileId).execute();
    }
}