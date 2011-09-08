<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>URL Shortener Server</title>
	<link rel="stylesheet" href="static/css/main.css" type="text/css"></link>
	<link rel="stylesheet" href="static/css/colors.css" type="text/css"></link>
	<link rel="stylesheet" href="static/css/local.css" type="text/css"></link>
	<link rel="stylesheet" href="static/css/apireference.css" type="text/css"></link>
	<link rel="stylesheet" href="static/css/codesite.css" type="text/css"></link>
</head>
<body>
	<div id="page">
		<div id="header">
			<div id="name-and-company">
				<div id='site-name'>
					<a href="" title="URL Shortener WS ${environmentName}" rel="home">URL Shortener Server ${environmentName}</a>
				</div>
				<div id='company-name'>
					<a href="http://code.google.com/p/openurlshortener/" title="code.google.com project" target="_blank">code.google.com project</a>
				</div>
			</div>
			<!-- /name-and-company -->
		</div>
		<!-- /header -->
		<div id="container">
			<div id="content" class="no-side-nav">

<h1>URL Shortener API on CloudFoundry.com</h1>	
		

<h2>The following web services are available to connect from a rest web service client:</h2>
<ul>
	<li>
		<p><strong>shortenURL:</strong> This service allows you to shorten URLs just as you would on <a href="#">ushrt.tk</a>.</p>
		<p> For example, to shorten the URL <code>http://www.google.com/</code>, send the following request: <code>http://url-shortener-ws.cloudfoundry.com/rest/shortenURL/http%3a%2f%2fwww.google.com%2f</code></p>
		
		<strong>API</strong>
		<pre>
			<code>
				http://url-shortener-ws.cloudfoundry.com/rest/shortenURL/{longUrl}
				Method: GET
				Content-Type: application/x-www-form-urlencoded
				Produces: text/plain
			</code>
		</pre>	
	</li>
	
	<li>
		<p><strong>expandURL:</strong> You can call this method to expand any <a href="#">ushrt.tk</a> short URL.</p>
		<p>For example, to expand <code>http://ushrt.tk/fbsS</code>, send the following request: <code>http://url-shortener-ws.cloudfoundry.com/rest/expandURL/http%3a%2f%2fushrt.tk%2ffbsS</code></p>
		
		<strong>API</strong>
		<pre>
			<code>
				http://url-shortener-ws.cloudfoundry.com/rest/expandURL/{shortUrl}
				Method: GET
				Content-Type: application/x-www-form-urlencoded
				Produces: text/plain
			</code>
		</pre>
	</li>
</ul>


<h2>The following services have been bound to this application:</h2>
<ul>
	<c:forEach items="${services}" var="service">
		<li><p>${service}</p></li>	
	</c:forEach>
</ul>

			</div>
		</div>
	</div>
</body>
</html>
