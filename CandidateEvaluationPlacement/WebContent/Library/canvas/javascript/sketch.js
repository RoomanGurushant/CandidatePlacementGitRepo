var ajax = new Ajax();
ajax.setEsperaMaxima(60000);

window.onload = startCanvas;

//Variaveis globais
var iexplorer = (document.all && !window.opera) ? true : false;

var ani;
var desenho;
var des_cod = [];

var canvasElem;

//abre uma janela div
function abrirJanela(jWidth, jHeight, jTitle){
	var jTop, jLeft;
	$("fundo").style.display = "block";
	$("janela_titulo").innerHTML = jTitle;
	var janelaElem = $("janela");
	janelaElem.style.display = "block";
	janelaElem.style.width = jWidth+'px';
	janelaElem.style.height = jHeight+'px';
    janelaElem.style.marginLeft = (-1 * janelaElem.offsetWidth / 2) + "px";
    janelaElem.style.marginTop = (-1 * janelaElem.offsetHeight / 2) + "px";
}

//fecha a janela atual
function fecharJanela(){
	$("fundo").style.display = "none";
	$("janela").style.display = "none";
}


//abre janela para alteração da dimenção
function dimensao() {
	$('janela_conteudo').innerHTML = '<form action="javascript:alterarDimencao()">'+
									 '<table width="100%">'+
									 '<tr><td>Width:</td><td><input type="text" size="3" id="dimLargura" autocomplete="off" value="'+desenho.getLargura()+'" />px</td></tr>'+
									 '<tr><td>Height:</td><td><input type="text" size="3" id="dimAltura" autocomplete="off" value="'+desenho.getAltura()+'" />px</td></tr>'+
									 '<tr><td colspan="2" align="center"><input type="submit" value="Ok" /></td></tr>'+
									 '</table>'+
									 '</form>';
	abrirJanela(180,130,'Attributes');
}

//altera a dimensão do desenho
function alterarDimencao() {
	var dimLargura = parseInt($('dimLargura').value);
	var dimAltura = parseInt($('dimAltura').value);
	
	if (!isNaN(dimLargura) && !isNaN(dimAltura)) {
		if (!(dimLargura > 501 || dimAltura > 334 || dimLargura < 100 || dimAltura < 100)) {
			$("canvas").width = $('dimLargura').value;
			$("canvas").height = $('dimAltura').value;
			$("canvasPrev").width = $('dimLargura').value;
			$("canvasPrev").height = $('dimAltura').value;
			$("canvasPrev").style.marginTop = '-' + $('dimAltura').value + 'px';
			$("areaCanvas").style.width = $('dimLargura').value + 'px';
			fecharJanela();
			
			var sequencia = desenho.getSequencia();
			preparaCanvas();
			desenho.setSequencia(sequencia);
		} else {
			alert('Width must be between 100px and 501px.\nHeight must be between 100px and 334px');
		}
	} else {
		alert('Invalid width or height!');
	}
}

function salvaDesenho(tipo){
	//verificando se não é o IE
	//if(!(document.all && !window.opera))
	//	var imagem = desenho.salvarPNG();

	$('janela_conteudo').innerHTML = 'Saving...';
	abrirJanela(200, 50, "Save");

	var dados = desenho.getSequencia();
	//document.getElementById("imagem").innerHTML = "Loading...";
	ajax.chamada('criar_img.php','t='+tipo+'&dados='+desenho.getSequencia()+'&width='+desenho.getLargura()+'&height='+desenho.getAltura(),'desenhoSalvo');
}

function desenhoSalvo(arq) {
	var partes = arq.split('-');
	
	if (partes[0] == '1') {
		//var titulo = "Clique com o bot&atilde;o direito e selecione 'Salvar Imagem'";
		location.href = 'download.php?nome='+partes[1];
		fecharJanela();
		//document.getElementById("imagem").innerHTML = "<img src=\"../imgs/praticar/"+partes[1]+"\" class=\"imgSalvar\" alt=\"" + titulo + "\" title=\"" + titulo + "\" />";
	} else {
		$('janela_conteudo').innerHTML = 'Error.';
		abrirJanela(100, 50, "Salvar");
		//document.getElementById("imagem").innerHTML = "Erro ao salvar imagem. Tente novamente";
	}
}

//--------------------------------------------------------------------------------------
//EVENTOS DA PÁGINA

//funcao de nome pequeno pra acessar elemento
function $(id) {
	return document.getElementById(id);
}

//--------------------------------------------------------------------------------------

//Inicialização do canvas
function startCanvas() {
	var telaElem = $("tela");
	//mostrando tela do jogo
    telaElem.style.display = "block";
    //centralizando tela
    telaElem.style.marginLeft = (-1 * telaElem.offsetWidth / 2) + "px";
    telaElem.style.marginTop = (-1 * $("contorno").offsetHeight / 2) + "px";
    telaElem.style.top = "50%";
    telaElem.style.left = "50%";

	preparaCanvas();
}

function preparaCanvas() {
	$("carregando").innerHTML = "";

	canvasElem = $("canvas");

	desenho = new Desenho(canvasElem,$("canvasPrev"),true);
	ani = new Animacao(desenho);

	desenho.onSelCor = function() {
		$("cor_escolhida").style.backgroundColor = this.getCorReal();
	}
	desenho.onStartDraw = function() {
		try {
			$("op_desfazer").style.backgroundColor = "";
		} catch(e) {}
	}
	ani.onComecar = function () {
		$("iniciarAni").style.display = "none";
		$("pararAni").style.display = "block";
	}
	ani.onPausar = function () {
		$("pararAni").style.display = "none";
		$("continuarAni").style.display = "block";
	}
	ani.onContinuar = function () {
		$("continuarAni").style.display = "none";
		$("pararAni").style.display = "block";
	}
	ani.onTerminar = function () {
		$("continuarAni").style.display = "none";
		$("pararAni").style.display = "none";
		$("iniciarAni").style.display = "block";
	}

	confPadrao();
	desenho.liberar(true);
}

//configuração padão do desenho
function confPadrao() {
    var cor = 1;
    var borda = 4;
    var tipo = 0;

    sel_opcao(tipo);
    sel_largura(borda);
    sel_cor(cor);
}

function sel_opcao(num) {
    var opcoes = [0,1,2,3,4,5,6,7,8];
    for(var cont = 0;cont < opcoes.length;cont++)
        $("op_"+opcoes[cont]).className = "op";

    $("op_"+num).className = "op_sel";

    desenho.mudaOpcao(num);
}

function sel_desfazer() {
	$("op_desfazer").style.backgroundColor = "#CCC";
	desenho.desfazer();
}

function sel_limpar() {
	$("op_desfazer").style.backgroundColor = "#CCC";
	desenho.limparTela(true);
}

function sel_largura(num) {
    var opcoes = [2,4,6,8,10];
    for(var cont = 0;cont < opcoes.length;cont++)
        $("op_larg"+opcoes[cont]).className = "op";

    $("op_larg"+num).className = "op_sel";

    desenho.mudaBorda(num, true);
}

function sel_cor(num) {
    desenho.mudaCor(num, true);
    $("cor_escolhida").style.backgroundColor = desenho.getCorReal();
}


function UTCTime() {
    var atual = new Date();
    return parseInt(Date.UTC(atual.getFullYear(),atual.getMonth(),atual.getDate(),atual.getHours(),atual.getMinutes(),atual.getSeconds()) / 1000);
}


//--------------------------------------------------------------------------------------
// CONTROLE DO AJAX
//--------------------------------------------------------------------------------------

// url_encode version 1.0
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