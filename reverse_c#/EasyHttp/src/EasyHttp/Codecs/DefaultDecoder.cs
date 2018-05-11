﻿using System;
using JsonFx.Serialization;
using JsonFx.Serialization.Providers;

namespace EasyHttp.Codecs
{
    public class DefaultDecoder: IDecoder
    {
        readonly IDataReaderProvider _dataReaderProvider;

        public DefaultDecoder(IDataReaderProvider dataReaderProvider)
        {
            _dataReaderProvider = dataReaderProvider;
        }

        public T DecodeToStatic<T>(string input, string contentType)
        {
            var parsedText = ReplaceAtSymbol(input);

            var deserializer = ObtainDeserializer(contentType);

            return deserializer.Read<T>(parsedText);

        }

        public dynamic DecodeToDynamic(string input, string contentType)
        {
            var parsedText = ReplaceAtSymbol(input);

            var deserializer = ObtainDeserializer(contentType);
       
            return deserializer.Read(parsedText);
        }

        public bool ShouldRemoveAtSign { get; set; }

        IDataReader ObtainDeserializer(string contentType)
        {
            var deserializer = _dataReaderProvider.Find(contentType);


            if (deserializer == null)
            {
                throw new SerializationException("The encoding requested does not have a corresponding decoder");
            }
            return deserializer;
        }

        private string ReplaceAtSymbol(string input)
        {
            if (!ShouldRemoveAtSign) return input;

            if (string.IsNullOrEmpty(input))
            {
                throw new ArgumentNullException("input");
            }

            var parsedText = input.Replace("\"@", "\"");

            return parsedText;
        }
    }
}