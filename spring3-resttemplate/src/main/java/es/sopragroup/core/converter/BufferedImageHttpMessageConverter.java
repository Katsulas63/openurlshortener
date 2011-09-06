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

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import javax.imageio.ImageIO;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

public class BufferedImageHttpMessageConverter implements HttpMessageConverter<BufferedImage> {

    public List<MediaType> getSupportedMediaTypes() {
        return Collections.singletonList(new MediaType("image", "jpeg"));
    }

    public boolean supports(Class<? extends BufferedImage> clazz) {
        return BufferedImage.class.equals(clazz);
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
	public BufferedImage read(Class<? extends BufferedImage> clazz,
			HttpInputMessage inputMessage) throws IOException,
			HttpMessageNotReadableException {
		return ImageIO.read(inputMessage.getBody());
	}

	/**
	 * {@inheritDoc}
	 */
	public void write(BufferedImage t, MediaType contentType,
			HttpOutputMessage outputMessage) throws IOException,
			HttpMessageNotWritableException {
		throw new UnsupportedOperationException("Not implemented");
	}

}
