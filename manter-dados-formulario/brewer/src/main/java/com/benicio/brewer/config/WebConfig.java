package com.benicio.brewer.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import com.benicio.brewer.controllers.CervejasController;

@Configuration // É uma classe de configuração/* @ComponentScan("com.benicio.brewer.controller") */ // Vunerável para erro de digitação
@ComponentScan(basePackageClasses = { CervejasController.class }) // Encontra os controllers que estão no mesmo pacote																	// da classe, {} array
@EnableWebMvc // Abilita esse projeto para o padrão mvc (assim consegue encontrar os controllers)
public class WebConfig extends WebMvcConfigurerAdapter/* Adaptadores para configuração - são facilitadores */ implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;

	}

	// Encontra as paginas html
	@Bean
	public ViewResolver viewResolver() { 
		ThymeleafViewResolver resolver = new ThymeleafViewResolver();
		resolver.setTemplateEngine(templateEngine());
		resolver.setCharacterEncoding("UTF-8");
		return resolver;
	}

	@Bean // Fica disponivel dentro do contexto do Spring
	public ITemplateEngine templateEngine() { // Processa os arquivos HTML
		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.setTemplateResolver(templateResolver()); // depende do templateResolver
		return engine;
	}
	
	@Bean
	public ITemplateResolver templateResolver() {
		SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver(); // resolver templates do Spring
		resolver.setApplicationContext(applicationContext);
		resolver.setPrefix("classpath:/templates/"); // onde estão meus templates
		resolver.setSuffix(".html");//encontra  a extensão da pagina pagina
		resolver.setTemplateMode(TemplateMode.HTML); // Estou trabalhando com html
		return resolver;
	}

}
