package com.xuyewei.community.provider;

import cn.ucloud.ufile.UfileClient;
import cn.ucloud.ufile.api.object.ObjectConfig;
import cn.ucloud.ufile.auth.ObjectAuthorization;
import cn.ucloud.ufile.auth.UfileObjectLocalAuthorization;
import cn.ucloud.ufile.bean.PutObjectResultBean;
import cn.ucloud.ufile.exception.UfileClientException;
import cn.ucloud.ufile.exception.UfileServerException;
import cn.ucloud.ufile.http.OnProgressListener;
import com.xuyewei.community.exception.CustomizeErrorCode;
import com.xuyewei.community.exception.CustomizeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.util.UUID;

/**
 * ClassName:UcloudProvider
 * Package:com.xuyewei.community.provider
 * Description:
 *
 * @Date:2019/12/19 21:17
 * @Author:xuyewei
 */
@Service
public class UCloudProvider {

  @Value("${ucloud.ufile.public-key}")
  private String publicKey;

  @Value("${ucloud.ufile.private-key}")
  private String privateKey;

  @Value("${ucloud.ufile.bucket-name}")
  private String bucketName;

  @Value("${ucloud.ufile.region}")
  private String region;

  @Value("${ucloud.ufile.suffix}")
  private String suffix;

  @Value("${ucloud.ufile.expires}")
  private Integer expires;


  public String upload(InputStream fileStream, String miniType, String fileName) {
    File file = new File("your file path");

    String generatedFileName;
    String[] filePaths = fileName.split("\\.");
    if (filePaths.length > 1) {
      generatedFileName = UUID.randomUUID().toString() + "." + filePaths[filePaths.length - 1];
    } else {
      throw new CustomizeException(CustomizeErrorCode.FILE_UPLOAD_FAIL);
    }

    try {
      // Bucket相关API的授权器
      ObjectAuthorization objectAuthorization = new UfileObjectLocalAuthorization(publicKey, privateKey);
      // 对象操作需要ObjectConfig来配置您的地区和域名后缀  captainxyw.cn-sh2.ufileos.com
      ObjectConfig config = new ObjectConfig(region, suffix);
      bucketName = "captainxyw";
      PutObjectResultBean response = UfileClient.object(objectAuthorization, config)
          .putObject(fileStream, miniType)
          .nameAs(generatedFileName)
          .toBucket(bucketName)
          .setOnProgressListener(new OnProgressListener() {
            @Override
            public void onProgress(long bytesWritten, long contentLength) {

            }
          })
          .execute();

      if (response != null && response.getRetCode() == 0) {
        String url = UfileClient.object(objectAuthorization, config)
            .getDownloadUrlFromPrivateBucket(generatedFileName, bucketName, expires)
            .createUrl();
        return url;
      } else {
        throw new CustomizeException(CustomizeErrorCode.FILE_UPLOAD_FAIL);
      }
    } catch (UfileClientException e) {
      e.printStackTrace();
      throw new CustomizeException(CustomizeErrorCode.FILE_UPLOAD_FAIL);
    } catch (UfileServerException e) {
      e.printStackTrace();
      throw new CustomizeException(CustomizeErrorCode.FILE_UPLOAD_FAIL);
    }
  }

}
