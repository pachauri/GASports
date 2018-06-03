package com.config;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.constants.GASportConstant;
import com.utils.GASportsUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

/**
 * @author vipul pachauri
 */
@Service
public class AmazonClient {

    private AmazonS3 s3client;

    private String accessKey;

    private String secretKey;

    @Value("${aws.email}")
    private String email;

    @Value("${aws.endpointUrl}")
    private String endpointUrl;

    @Value("${aws.bucketName}")
    private String bucketName;

    @Value("${aws.configPath}")
    private String configPath;

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKeyId) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndpointUrl() {
        return endpointUrl;
    }

    public void setEndpointUrl(String endpointUrl) {
        this.endpointUrl = endpointUrl;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    @PostConstruct
    private void initializeAmazon() {
        ClientConfiguration clientCfg = new ClientConfiguration();
        clientCfg.setConnectionTimeout(ClientConfiguration.DEFAULT_CONNECTION_TIMEOUT);
        clientCfg.setSocketTimeout(GASportConstant.S3_SOCKET_TIMEOUT);
        //Set access key & secret key
        fromJSON();
        BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        AmazonS3 s3Client = new AmazonS3Client(basicAWSCredentials,clientCfg);
        this.s3client = s3Client;
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    private String generateFileName(File file) {
        return new Date().getTime() + "-" + file.getName().replace(" ", "_");
    }

    private void uploadFileTos3bucket(String fileName, File file) {
        System.out.println("Uploading file.");
        s3client.putObject(new PutObjectRequest(bucketName, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        System.out.println("File Uploaded.");
    }

    public String uploadFile(File file) {

        String fileUrl = "";
        try {
            String fileName = generateFileName(file);
            fileUrl = endpointUrl + "/" + bucketName + "/" + fileName;
            uploadFileTos3bucket(fileName, file);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileUrl;
    }

    public String deleteFileFromS3Bucket(String fileUrl) {
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        s3client.deleteObject(new DeleteObjectRequest(bucketName + "/", fileName));
        return "Successfully deleted";
    }

    private void fromJSON() {
        JSONParser parser = new JSONParser();
        try {
            Object object =  parser.parse(new FileReader(configPath));
            JSONObject jsonObject = (JSONObject) object;
            JSONObject config = (JSONObject) jsonObject.get("awsConfig");
            String accessKey = (String) config.get("accessKey");
            this.accessKey = accessKey;
            String secretKey = (String) config.get("secretKey");
            this.secretKey = secretKey;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
