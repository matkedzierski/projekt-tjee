package emotki;

import java.util.Date;
import org.bson.types.ObjectId;

public class Emotikona {
    private ObjectId id;
    private String oriFileName;
    private String ext;
    private String desc;
    private Date added;
    private String content;
    private String thumbnail;
    private String bigsize;
    private boolean deleted;

    public Emotikona() {
    }
    
    public Emotikona(String desc, String filename, String ext, String content, String thumbnail, String bigsize) {
        this.desc = desc;
        this.ext = ext;
        this.oriFileName= filename;
        this.added = new Date();
        this.content = content;
        this.deleted = false;
        this.bigsize = bigsize;
        this.thumbnail = thumbnail;
    }

    public String getBigsize() {
        return bigsize;
    }

    public void setBigsize(String bigsize) {
        this.bigsize = bigsize;
    }
    
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getOriFileName() {
        return oriFileName;
    }

    public void setOriFileName(String oriFileName) {
        this.oriFileName = oriFileName;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }
    
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getAdded() {
        return added;
    }

    public void setAdded(Date added) {
        this.added = added;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
            
    
            
}
