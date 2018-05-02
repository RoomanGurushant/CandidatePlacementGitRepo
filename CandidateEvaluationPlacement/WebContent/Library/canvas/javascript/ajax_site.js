/*
 * Desenvolvido por Henrique Moreira
 * Proibida a cópia
 */

function Ajax()
{
	//Variaveis globais (privadas)
	var self = this;
	var vez = null;
	var fila = new Array();
	var method = "POST";
	var lastTime = 0;
	var assincrono = true;
	var inicialTime;
	var xmlhttp;
	var esperaMaxima = 5000;
	var erros = 0;

	novoObjeto();
	setInterval(function() { rotina() },2000);

	//cria objeto ajax
	function novoObjeto(){
		try{
			xmlhttp = new XMLHttpRequest();
		}
		catch(ee){
			try{
				xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
			}
			catch(e){
				try{
					xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
				}
				catch(E){
					xmlhttp = false;
				}
			}
		}
	}

	//objeto de requisição
	function objRequest(pag,param,func,met,semCache) {
		this.pag = pag;
		this.param = param;
		this.func = func;
		this.method = met;
		this.semCache = semCache;
	}

	//calcula tempo atual
	function UTCTime() {
		var atual = new Date();
		return Date.UTC(atual.getFullYear(),atual.getMonth(),atual.getDate(),atual.getHours(),atual.getMinutes(),atual.getSeconds(),atual.getMilliseconds());
	}
	
	function andarFila() {
		lastTime = UTCTime();

		if(fila.length > 0)	{
			vez = fila.shift();
			delay();
		}
		else
			vez = null;	
	}

	//funcao que realiza requisição
	function ajaxRequest() {
		inicialTime = UTCTime();

		if (vez.method == null)
			xmlhttp.open("POST", vez.pag + "?rand=" + UTCTime(), assincrono);
		else {
			if(!vez.semCache) {
				var parametro = (vez.param.length > 0) ? '&'+vez.param : '';
				xmlhttp.open("GET", vez.pag + "?x=" + UTCTime() + parametro,assincrono);
			} else {
				var parametro = (vez.param.length > 0) ? vez.param : '';
				xmlhttp.open("GET", vez.pag + "?" + parametro,assincrono);
			}
		}

		//bug firefox
		try {
			xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		}
		catch(e) {

		}

		xmlhttp.onreadystatechange = function(){
			if (xmlhttp.readyState == 4){
				var status = 0;

				try
				{
					status = xmlhttp.status;
				}
				catch(e)
				{
					novoObjeto();
					return;
				}

				//caso seja uma pagina valida
				if(status == 200)
				{
					//zerando erros
					erros = 0;
					
					if(vez.func != null)
					{
						var texto = xmlhttp.responseText;
						if(vez.func instanceof Function)
							vez.func(texto);
						else
							eval(vez.func+"(texto)");
					}

					andarFila();
				}
				// caso ocora algum erro...

				else if(status != 0) {
					//verificando se atingiu limite de erros
					if (++erros < 5) {
						inicialTime = UTCTime();
						setTimeout(function(){
							self.abortar()
						}, 10000);
					} else {
						erros = 0;
						//alert('Erro');
						
						andarFila();
					}
				}

			}
		};
		//bug firefox
		try {
			if(method == "POST")
				xmlhttp.send(vez.param);
			else
				xmlhttp.send(null);
		}
		catch(e) {

		}
	}


	function delay()
	{
		var diferenca = UTCTime() - lastTime;

		if(diferenca > 700)
			ajaxRequest();
		else
		{
			diferenca = 700 - diferenca;
			setTimeout(function() { ajaxRequest() },diferenca);
		}
	}
	
	function rotina()
	{
		if(vez != null)
		{
			var diferenca = UTCTime() - inicialTime;

			if(diferenca > esperaMaxima)
			    self.abortar();
		}
	}

	this.chamada = function (pag,param,func,met,semCache){
		if(vez == null)
		{
			//adiciona para a chamada atual
			vez = new objRequest(pag,param,func,met,semCache);
			//delay, if need
			delay();
		}
		else
		{
			//adiciona chamada na fila
			fila.push(new objRequest(pag,param,func,met,semCache));
		}
	}

	//altera metodo
	this.setMethod = function(metodo) {
		method = metodo;
	}

	//obtem metodo
	this.getMethod = function() {
		return method;
	}

	//aborta chamada ajax
	this.abortar = function(){
		xmlhttp.abort();
		ajaxRequest();
	}

	//altera tipo do ajax
	this.setAssicrono = function(val) {
		assicrono = val;
	}
	
	//altera tipo do ajax
	this.setEsperaMaxima = function(val) {
		esperaMaxima = val;
	}

	//obtem tipo do ajax
	this.getAssicrono = function() {
		return assincrono;
	}

	this.isVezNull = function() {
		return (vez == null);
	}
}

function url_encode(str) {
    var hex_chars = "0123456789ABCDEF";
    var noEncode = /^([a-zA-Z0-9\_\-\.])$/;
    var n, strCode, hex1, hex2, strEncode = "";

    for(n = 0; n < str.length; n++) {
        if (noEncode.test(str.charAt(n))) {
            strEncode += str.charAt(n);
        } else {
		        strCode = str.charCodeAt(n);
		        hex1 = hex_chars.charAt(Math.floor(strCode / 16));
		        hex2 = hex_chars.charAt(strCode % 16);
		        strEncode += "%" + (hex1 + hex2);
		    }
		}
return strEncode;
}