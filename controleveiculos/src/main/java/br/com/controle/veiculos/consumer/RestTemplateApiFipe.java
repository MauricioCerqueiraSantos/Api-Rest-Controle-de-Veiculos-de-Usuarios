package br.com.controle.veiculos.consumer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@FeignClient
public class RestTemplateApiFipe {
	
	
//	
//	public void consumerFipe() {
//		RestTemplateApiFipe template = new RestTemplateApiFipe();
//		
//		//Marcas// https://parallelum.com.br/fipe/api/v1/carros/marcas
//		//Modelos// https://parallelum.com.br/fipe/api/v1/carros/marcas/59/modelos
//		//Anos// https://parallelum.com.br/fipe/api/v1/carros/marcas/59/modelos/5940/anos
//		//Valor//https://parallelum.com.br/fipe/api/v1/carros/marcas/59/modelos/5940/anos/2014-3
//		
//		UriComponents uri = UriComponentsBuilder.newInstance()
//				.scheme("https")
//				.host("parallelum.com.br")
//				.path("fipe/api/v1/carros/marcas")
//				//.queryParam(name: "carros", values: "marcas")
//				.build();
//		
//		ResponseEntity<Fipe> entity = template.get((uri.toUriString(), Fipe.class));		
//	}
	
	List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();        
	//Add the Jackson Message converter
	MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

	// Note: here we are making this converter to process any kind of response, 
	// not only application/*json, which is the default behaviour
//	converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));        
//	messageConverters.add(converter);  
//	template.setMessageConverters(messageConverters); 
	
	
	
	
	public static void main(String[] args) {
		RestTemplate template = new RestTemplateBuilder().rootUri("https://parallelum.com.br/fipe/api/v1/carros/marcas").build();
		//template.getForObject("https://parallelum.com.br/fipe/api/v1/carros", Fipe.class);
		Fipe fipe = template.getForObject("https://parallelum.com.br/fipe/api/v1/carros/marcas", Fipe.class);

		System.out.println(fipe);
	}
	
	

}
