package com.main;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

public class SitemeshFilter extends ConfigurableSiteMeshFilter {

	@Override
	protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
		builder.addDecoratorPath("/**", "layout.jsp");
//		builder.addDecoratorPath("/admin", "/WEB-INF/decorator/layout1.jsp")
//				.addExcludedPath("/sitemap");
	}
	
	

}
