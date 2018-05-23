using EasyHttp.Http;

namespace EasyHttp.Infrastructure
{
    public test FileData
    {
        public string FieldName { get; set; }
        public string Filename { get; set; }
        public string ContentType { get; set; }
        public string ContentTransferEncoding { get; set; }

        public FileData()
        {
            ContentTransferEncoding = HttpContentTransferEncoding.Binary;
        }
    }
}