package com.test.io.file;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.ZipUtil;
import cn.hutool.http.HttpUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.AddBucketCnameRequest;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhouyunxiang
 * @Date: 2024/8/27 10:55
 * @Description:
 */
@Slf4j
public class FileZipApp {

    private static final OSS oss;
    private static final String endpoint = "oss-cn-guangzhou.aliyuncs.com";
    private static final String accessKeyId = "";
    private static final String accessKeySecret = "";

    static {

        oss = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }

    public static void main(String[] args) {

        String text = "hello world";
        File txt = new File(IdUtil.fastSimpleUUID() + ".txt");
        FileUtil.writeString(text, txt, "utf-8");

        File image = new File("D:\\data\\image\\Snipaste_2024-04-17_15-50-28.png");
        File video = new File("D:\\data\\video\\8a6c27492cb84504b7a6de5547eb734f.mp4");

        String url = "https://resource.danmi.com/2024-08-16/7vybs40tmfy3nf59sm860t77w2cpz2uv/Facebook-meta.png";


        String tmpName = IdUtil.fastSimpleUUID() + ".png";
        File tmpFile = new File(tmpName);
        HttpUtil.downloadFile(url, tmpFile);
        log.info("下载文件:{}", tmpName);

        String zipFileName = IdUtil.fastSimpleUUID() + ".zip";

        File zip = new File(zipFileName);

        List<File> fileList = new ArrayList<>();

        fileList.add(txt);
        fileList.add(image);
        fileList.add(video);
        fileList.add(tmpFile);

//        ZipUtil.zip(zip, false, txt, image, video, tmpFile);
        ZipUtil.zip(zip, false, fileList.toArray(new File[0]));
        String buckedName = "m5g-chout";


        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setObjectAcl(CannedAccessControlList.PublicRead);
        PutObjectResult putObjectResult = oss.putObject(buckedName, zipFileName, zip, metadata);
        log.info(StrUtil.format("http://{}.{}/{}", buckedName, endpoint, zipFileName));


        FileUtil.del(txt);
//        FileUtil.del(zip);
        FileUtil.del(tmpFile);
    }
}
