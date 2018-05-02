/*
 * Desenvolvido por Henrique Moreira
 * Proibida a cópia
 */

//----------------------------------------
// OBJETO PARA CRIAR ANIMAÇÕES DE DESENHO
// Depende de um objeto Desenho instanciado
//----------------------------------------

function Animacao(desenho, fracoes) {
	var self = this;
	var passo;
	var rodando = false;
	var velocidade1 = 200;
	var velocidade2 = 40;
	var velocidadeDireto = 4;
	var velocidadeAtual = velocidade1;
	var direto = velocidadeDireto - 1;
	var diretoVez = false;
	var cursor = true;
	var desenhoLiberado = true;
	var atualizar = false;
	
	var totalTempo = 0;
	var mudarTempo = false;
	var mudarTempoRef = 0;
	var fracoesDesenho = [];
	
	var tempRodar = "";
	var divisor = 1;
	var seq = "";
	var desenho = desenho;
	var stackArr = [];

	//Trata cada passo da animação
	function rodar(){
		if (rodando) {
			desenhoTela();

			passo++;
			//atualizando referencia do desenho
			if(atualizar) {
				seq = desenho.getSequencia().split("|");
				atualizar = false;
			}
			
			if (passo < seq.length) {
				if (!diretoVez && direto == velocidadeDireto - 1 && !mudarTempo) {
					setTimeout(function(){
						rodar();
					}, velocidadeAtual);
					
					totalTempo += velocidadeAtual;
					if(self.onTempo instanceof Function) self.onTempo(totalTempo);
				} else {
					if (mudarTempo) {
						totalTempo += velocidadeAtual;
						if (mudarTempoRef <= totalTempo) 
							mudarTempo = false;
					}
					
					rodar();
				}
			} else {
				rodando = false;
				desenho.limparTelaPrev();
				//liberando o ato de desenhar caso seja a questao
				desenho.liberar(desenhoLiberado);
				if(self.onTerminar instanceof Function) self.onTerminar();
			}
		}
	}

	//executa o desenho na tela
	function desenhoTela(){
		if(!rodando) return;
		
		var vez = seq[passo].split("@");
		var vals = vez[1].split("#");
		velocidadeAtual = velocidade1;
		diretoVez = false;

		switch (parseInt(vez[0])) {
			//retangulo, circulo, linha...
			case 1:
				desenho.desenhar(Math.round(parseInt(vals[1]) / divisor), Math.round(parseInt(vals[2]) / divisor), Math.round(parseInt(vals[3]) / divisor), Math.round(parseInt(vals[4]) / divisor), parseInt(vals[0]), 0, false);
				ponteiro(Math.round(parseInt(vals[3]) / divisor), Math.round(parseInt(vals[4]) / divisor));
				break;
			//lápis
			case 2:
				if (vals.length >= 4) {
					//se for as primeiras coordenadas, pegar primeiras coordenadas
					if (!stackArr.length) 
						stackArr.push([Math.round(parseInt(vals[0]) / divisor), Math.round(parseInt(vals[1]) / divisor)]);
					stackArr.push([Math.round(parseInt(vals[2]) / divisor), Math.round(parseInt(vals[3]) / divisor)]);
					
					if (vals.length > 4) {
						seq[passo] = "2@" + vals.slice(2).join("#");
						passo--;
						//acelerando
						velocidadeAtual = velocidade2;
						//passar com ou sem tempo alternado (para ficar mais rapido)
						direto = (direto + 1) % velocidadeDireto;
					}
					else {
						//desenhando linha final
						desenho.limparTelaPrev();
						desenho.linhaSeq(stackArr, 0);
						//zerando pilha da linha
						stackArr = [];						
						//trocando a velocidade pra nao ser direto
						direto = velocidadeDireto - 1;
						//verificar se o proximo tambem é lapis, para manter a velocidade
						if (seq[passo + 1] != undefined && seq[passo].charAt(0) == seq[passo + 1].charAt(0)) {
							velocidadeAtual = velocidade2;
						}
					}
					
					//desenhando ponteiro
					ponteiro(Math.round(parseInt(vals[2]) / divisor), Math.round(parseInt(vals[3]) / divisor));
				}
				else {
					direto = velocidadeDireto - 1;
					stackArr = [];
				}
				break;
			//balde
			case 3:
				var x1, y1, x2, y2;

				for (var cont = 0; cont < vals.length; cont = cont + 4) {
					x1 = Math.round(parseInt(vals[cont]) / divisor);
					y1 = Math.round(parseInt(vals[cont + 1]) / divisor);
					x2 = Math.round(parseInt(vals[cont + 2]) / divisor);
					y2 = Math.round(parseInt(vals[cont + 3]) / divisor);
					desenho.retanguloF(x1, y1, x2, y2, 0);
				}
				break;
			case 4:
				desenho.limparTela(false);
				break;
			case 21:
				desenho.borracha(Math.round(parseInt(vals[0]) / divisor), Math.round(parseInt(vals[1]) / divisor), 0, false);
				ponteiro(Math.round(parseInt(vals[2]) / divisor), Math.round(parseInt(vals[3]) / divisor));

				if (vals.length >= 4) {
					seq[passo] = "21@" + vals.slice(2).join("#");
					passo--;
					//acelerando
					velocidadeAtual = velocidade2;
				}
				else
					if (seq[passo + 1] != undefined && seq[passo].substr(0, 2) == seq[passo + 1].substr(0, 2)) {
						velocidadeAtual = velocidade2;
					}
				break;
			//mudar cor
			case 5:
				desenho.mudaCor(vals[0], false);
				diretoVez = true; //direto
				break;
			//mudar borda
			case 6:
				var valor = Math.round(parseInt(vals[0]) / divisor);
				desenho.mudaBorda(valor, false);
				diretoVez = true; //direto
				break;
			//mudar alpha
			case 27:
				desenho.mudaAlpha(vals[0],false);
				diretoVez = true; //direto
				break;
			//mudar imagem base
			case 26:
				desenho.transparencia();
				desenho.setArquivoBase(vals[0],false);
			break;
		}
	}
	
	function ponteiro(cx,cy) {
		if(cursor && desenho.hasCanvasPrev() && cx > 0 && cy > 0) {
			desenho.limparTelaPrev();
			//se estiver no processo de desenhar linha...
			if(stackArr.length > 0) {
				//desenhando linha
				desenho.linhaSeq(stackArr, 1);
			}
			
			//valores originais
			var corDesenho = desenho.getCorValor();
			var bordaDesenho = desenho.getBorda();
			var alphaDesenho = desenho.getAlpha();
			
			desenho.mudaAlpha(0.7,false);
			desenho.mudaBorda(2,false);
			desenho.mudaCor('xDDDDDD',false);
			desenho.desenhar(cx-5,cy-5,cx+5,cy+5,4,1,false);
			desenho.mudaCor('x999999',false);
			desenho.desenhar(cx-5,cy-5,cx+5,cy+5,5,1,false);
			
			//retornando dados
			desenho.mudaCor(corDesenho,false);
			desenho.mudaBorda(bordaDesenho,false);
			desenho.mudaAlpha(alphaDesenho,false);
		} else {
			//se estiver no processo de desenhar linha...
			if(stackArr.length > 0) {
				//desenhando linha
				desenho.limparTelaPrev();
				desenho.linhaSeq(stackArr, 1);
			}
		}
	}

	//Iniciar a animação
	this.iniciar = function(div){
		if(!rodando) {
			rodando = true;
			divisor = div;

			//limpando tela
			desenho.limparTela(false);
			if(self.onComecar instanceof Function) self.onComecar();
			seq = desenho.getSequencia().split("|");

			//desabilitar novos desenhos
			desenhoLiberado = desenho.getLiberado();
			desenho.liberar(false);
			
			//cor, borda e alpha default
			desenho.mudaCor('x000000', false);
			desenho.mudaBorda(4, false);
			desenho.mudaAlpha(1, false);

			totalTempo = 0;
			passo = 0;
			direto = velocidadeDireto - 1;
			stackArr = [];
			setTimeout(function() { rodar(); }, velocidade1);
		}
	}

	//Pausar a animação
	this.pausar = function(){
		if(rodando) {
			rodando = false;
			desenho.limparTelaPrev();
			if(self.onPausar instanceof Function) self.onPausar();
		}
	}

	//cancela animacao e exibe desenho completo
	this.parar = function(){
		rodando = false;
		while(passo < seq.length) {
			desenhoTela();
			passo++;
		}
		//liberando o ato de desenhar caso seja a questao
		desenho.liberar(desenhoLiberado);
		desenho.limparTelaPrev();
		if(self.onTerminar instanceof Function) self.onTerminar();
	}

	//continuar animação que foi paralizada
	this.continuar = function() {
		if(!rodando) {
			rodando = true;
			if(self.onContinuar instanceof Function) self.onContinuar();
			rodar();
		}
	}
	
	this.getVelocidade1 = function() {
		return velocidade1;
	}
	
	this.getVelocidade2 = function() {
		return velocidade2;
	}
	
	this.getVelocidadeDireto = function() {
		return velocidadeDireto;
	}
	
	this.setVelocidades = function(vel1, vel2, veld) {
		velocidade1 = vel1;
		velocidade2 = vel2;
		velocidadeDireto = veld;
	}
	
	this.setCursor = function(val) {
		cursor = val;
		if(!cursor)
			desenho.limparTelaPrev();
	}
	
	//atualizar sequencia de tempos em tempos
	this.setAtualizar = function() {
		atualizar = true;
	} 
	
	this.setTempo = function(tempoRef) {
		mudarTempoRef = tempoRef;
		mudarTempo = true;
		this.parar();
		this.iniciar(1);
	}

	//Eventos da classe
	this.onTerminar = null;
	this.onComecar = null;
	this.onPausar = null;
	this.onContinuar = null;
	this.onTempo = null;
}