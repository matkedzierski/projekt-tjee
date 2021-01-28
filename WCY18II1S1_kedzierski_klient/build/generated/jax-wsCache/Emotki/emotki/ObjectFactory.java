
package emotki;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the emotki package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetIDs_QNAME = new QName("http://emotki/", "getIDs");
    private final static QName _GetFiles_QNAME = new QName("http://emotki/", "getFiles");
    private final static QName _DeleteFile_QNAME = new QName("http://emotki/", "deleteFile");
    private final static QName _GetBigImage_QNAME = new QName("http://emotki/", "getBigImage");
    private final static QName _GetFileInfoResponse_QNAME = new QName("http://emotki/", "getFileInfoResponse");
    private final static QName _PostFile_QNAME = new QName("http://emotki/", "postFile");
    private final static QName _PostFileResponse_QNAME = new QName("http://emotki/", "postFileResponse");
    private final static QName _GetBigImageResponse_QNAME = new QName("http://emotki/", "getBigImageResponse");
    private final static QName _GetFileContent_QNAME = new QName("http://emotki/", "getFileContent");
    private final static QName _GetFileInfo_QNAME = new QName("http://emotki/", "getFileInfo");
    private final static QName _DeleteFileResponse_QNAME = new QName("http://emotki/", "deleteFileResponse");
    private final static QName _GetFilesResponse_QNAME = new QName("http://emotki/", "getFilesResponse");
    private final static QName _GetIDsResponse_QNAME = new QName("http://emotki/", "getIDsResponse");
    private final static QName _GetFileContentResponse_QNAME = new QName("http://emotki/", "getFileContentResponse");
    private final static QName _PostFileContent_QNAME = new QName("", "content");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: emotki
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DeleteFile }
     * 
     */
    public DeleteFile createDeleteFile() {
        return new DeleteFile();
    }

    /**
     * Create an instance of {@link GetFileInfoResponse }
     * 
     */
    public GetFileInfoResponse createGetFileInfoResponse() {
        return new GetFileInfoResponse();
    }

    /**
     * Create an instance of {@link PostFile }
     * 
     */
    public PostFile createPostFile() {
        return new PostFile();
    }

    /**
     * Create an instance of {@link GetBigImage }
     * 
     */
    public GetBigImage createGetBigImage() {
        return new GetBigImage();
    }

    /**
     * Create an instance of {@link GetIDs }
     * 
     */
    public GetIDs createGetIDs() {
        return new GetIDs();
    }

    /**
     * Create an instance of {@link GetFiles }
     * 
     */
    public GetFiles createGetFiles() {
        return new GetFiles();
    }

    /**
     * Create an instance of {@link DeleteFileResponse }
     * 
     */
    public DeleteFileResponse createDeleteFileResponse() {
        return new DeleteFileResponse();
    }

    /**
     * Create an instance of {@link GetFileContentResponse }
     * 
     */
    public GetFileContentResponse createGetFileContentResponse() {
        return new GetFileContentResponse();
    }

    /**
     * Create an instance of {@link GetFilesResponse }
     * 
     */
    public GetFilesResponse createGetFilesResponse() {
        return new GetFilesResponse();
    }

    /**
     * Create an instance of {@link GetIDsResponse }
     * 
     */
    public GetIDsResponse createGetIDsResponse() {
        return new GetIDsResponse();
    }

    /**
     * Create an instance of {@link GetBigImageResponse }
     * 
     */
    public GetBigImageResponse createGetBigImageResponse() {
        return new GetBigImageResponse();
    }

    /**
     * Create an instance of {@link GetFileContent }
     * 
     */
    public GetFileContent createGetFileContent() {
        return new GetFileContent();
    }

    /**
     * Create an instance of {@link GetFileInfo }
     * 
     */
    public GetFileInfo createGetFileInfo() {
        return new GetFileInfo();
    }

    /**
     * Create an instance of {@link PostFileResponse }
     * 
     */
    public PostFileResponse createPostFileResponse() {
        return new PostFileResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetIDs }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://emotki/", name = "getIDs")
    public JAXBElement<GetIDs> createGetIDs(GetIDs value) {
        return new JAXBElement<GetIDs>(_GetIDs_QNAME, GetIDs.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFiles }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://emotki/", name = "getFiles")
    public JAXBElement<GetFiles> createGetFiles(GetFiles value) {
        return new JAXBElement<GetFiles>(_GetFiles_QNAME, GetFiles.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteFile }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://emotki/", name = "deleteFile")
    public JAXBElement<DeleteFile> createDeleteFile(DeleteFile value) {
        return new JAXBElement<DeleteFile>(_DeleteFile_QNAME, DeleteFile.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBigImage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://emotki/", name = "getBigImage")
    public JAXBElement<GetBigImage> createGetBigImage(GetBigImage value) {
        return new JAXBElement<GetBigImage>(_GetBigImage_QNAME, GetBigImage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFileInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://emotki/", name = "getFileInfoResponse")
    public JAXBElement<GetFileInfoResponse> createGetFileInfoResponse(GetFileInfoResponse value) {
        return new JAXBElement<GetFileInfoResponse>(_GetFileInfoResponse_QNAME, GetFileInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PostFile }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://emotki/", name = "postFile")
    public JAXBElement<PostFile> createPostFile(PostFile value) {
        return new JAXBElement<PostFile>(_PostFile_QNAME, PostFile.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PostFileResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://emotki/", name = "postFileResponse")
    public JAXBElement<PostFileResponse> createPostFileResponse(PostFileResponse value) {
        return new JAXBElement<PostFileResponse>(_PostFileResponse_QNAME, PostFileResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBigImageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://emotki/", name = "getBigImageResponse")
    public JAXBElement<GetBigImageResponse> createGetBigImageResponse(GetBigImageResponse value) {
        return new JAXBElement<GetBigImageResponse>(_GetBigImageResponse_QNAME, GetBigImageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFileContent }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://emotki/", name = "getFileContent")
    public JAXBElement<GetFileContent> createGetFileContent(GetFileContent value) {
        return new JAXBElement<GetFileContent>(_GetFileContent_QNAME, GetFileContent.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFileInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://emotki/", name = "getFileInfo")
    public JAXBElement<GetFileInfo> createGetFileInfo(GetFileInfo value) {
        return new JAXBElement<GetFileInfo>(_GetFileInfo_QNAME, GetFileInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteFileResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://emotki/", name = "deleteFileResponse")
    public JAXBElement<DeleteFileResponse> createDeleteFileResponse(DeleteFileResponse value) {
        return new JAXBElement<DeleteFileResponse>(_DeleteFileResponse_QNAME, DeleteFileResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFilesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://emotki/", name = "getFilesResponse")
    public JAXBElement<GetFilesResponse> createGetFilesResponse(GetFilesResponse value) {
        return new JAXBElement<GetFilesResponse>(_GetFilesResponse_QNAME, GetFilesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetIDsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://emotki/", name = "getIDsResponse")
    public JAXBElement<GetIDsResponse> createGetIDsResponse(GetIDsResponse value) {
        return new JAXBElement<GetIDsResponse>(_GetIDsResponse_QNAME, GetIDsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFileContentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://emotki/", name = "getFileContentResponse")
    public JAXBElement<GetFileContentResponse> createGetFileContentResponse(GetFileContentResponse value) {
        return new JAXBElement<GetFileContentResponse>(_GetFileContentResponse_QNAME, GetFileContentResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "content", scope = PostFile.class)
    public JAXBElement<byte[]> createPostFileContent(byte[] value) {
        return new JAXBElement<byte[]>(_PostFileContent_QNAME, byte[].class, PostFile.class, ((byte[]) value));
    }

}
