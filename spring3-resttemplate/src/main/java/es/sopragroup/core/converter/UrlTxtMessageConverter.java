/*
 * Copyright 2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package es.sopragroup.core.converter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

public class UrlTxtMessageConverter implements HttpMessageConverter<String> {
	
	public static String LS = System.getProperty("line.separator");

    public List<MediaType> getSupportedMediaTypes() {
        return Collections.singletonList(new MediaType("text", "plain"));
    }

    public boolean supports(Class<? extends Object> clazz) {
        return Object.class.equals(clazz);
    }
    
    /**
	 * {@inheritDoc}
	 */
	public boolean canRead(Class<?> clazz, MediaType mediaType) {
		return Boolean.TRUE;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean canWrite(Class<?> clazz, MediaType mediaType) {
		return Boolean.FALSE;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public String read(Class<? extends String> clazz,
			HttpInputMessage inputMessage) throws IOException,
			HttpMessageNotReadableException {
		
		final String data = convertInputStreamToString(inputMessage.getBody());		
		
		return data;
	}

	/**
	 * {@inheritDoc}
	 */
	public void write(String t, MediaType contentType,
			HttpOutputMessage outputMessage) throws IOException,
			HttpMessageNotWritableException {
		throw new UnsupportedOperationException("Not implemented");
	}
	
	public static String convertInputStreamToString(InputStream io) {
        final StringBuilder sb = new StringBuilder();
        try {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(io, "UTF-8"));
            String line = reader.readLine();
            while (line != null) {
                sb.append(line).append(LS);
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("No se pudo obtener un InputStream", e);

        }
        return sb.toString();
    }

}
