package io.marcus.robot.model;


import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Component
public class Medication {
    private String name;
    private float weight;
    private String code;
    private byte[] imgBytes;
    private String imgPath;

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setWeight(float weight){
        this.weight = weight;
    }

    public float getWeight(){
        return this.weight;
    }

    public void setCode(String code){
        this.code = code;
    }

    public String getCode(){
        return this.code;
    }

    public float getMedWeight(){
        return this.weight;
    }

    public void setImgPath(String imgpath){
        this.imgPath = imgPath;
        this.imgBytes = imgPathToBytes(imgPath);
    }

    

    public byte[] imgPathToBytes(String imgPath){

        ByteArrayOutputStream baos = null;

        try{
            BufferedImage originalImage = ImageIO.read(new File(imgPath)); //Paths.get(imagePath)
            baos = new ByteArrayOutputStream();
            ImageIO.write( originalImage, "jpg", baos);
            baos.flush();
            this.imgBytes = baos.toByteArray();
            
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        finally{
            try{
                baos.close(); 
            }
            catch(IOException e){
                System.out.println(e.getMessage());
            }
        } 
        return this.imgBytes;   
        
    }

    // @Autowired
    // public Medication(String name, float weight, String code, String imgPath) {
    //     this.name = name;
    //     this.weight = weight;
    //     this.code = code;

    //     ByteArrayOutputStream baos = null;

    //     try{
    //         BufferedImage originalImage = ImageIO.read(new File(imgPath)); //Paths.get(imagePath)
    //         baos = new ByteArrayOutputStream();
    //         ImageIO.write( originalImage, "jpg", baos);
    //         baos.flush();
    //         imgBytes = baos.toByteArray();
    //     }
    //     catch(IOException e){
    //         System.out.println(e.getMessage());
    //     }
    //     finally{
    //         try{
    //             baos.close(); 
    //         }
    //         catch(IOException e){
    //             System.out.println(e.getMessage());
    //         }
    //     }    
        
    // }
    
    // @Bean
    // public static Medication create(String name, float weight, String code, String imgPath){
    //     return new Medication(name, weight, code, imgPath);
    // }

 
}
