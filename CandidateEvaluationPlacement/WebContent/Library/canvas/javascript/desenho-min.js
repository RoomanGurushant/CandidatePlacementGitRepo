function Desenho(e,l,J){
var j=this;var o=(document.all&&!window.opera);
var q=false;
var N=false;
var p=false;
var v="";
var h=[];
var t="";
var i=e.getContext("2d");
var K,G,a;
var I=[i];
var d=e.offsetWidth;
var y=e.offsetHeight;
var g=1;
var L=4;
var H=0;
var f=["rgba(0,0,0,0)","#000","#666","#8b0000","#008b00","#00008b","#008b8b","#8b8b00","#8b4500","#8b0a50","#551a8b","#548b54","#8b6969","#8b7b8b","#fff","#ccc","#ff0000","#00ff00","#0000ff","#00ffff","#ffff00","#ff7f00","#ff1493","#9b30ff","#9aff9a","#ffc1c1","#ffe1ff"];
var M;
var s;
var b;
var z;
var A=new Array();
var w=new Array();
var O=new Array();
this.onSelCor=null;
this.onCodigo=null;
this.onStartDraw=null;
function C(P){
return document.getElementById(P)}this.borracha=function(S,R,T,U){
S-=L*3;
R-=L*3;
I[T].clearRect(S,R,L*6+1,L*6+1);
for(var P=S;P<=S+L*6;P++){
for(var Q=R;Q<=R+L*6;Q++){
if(P>=0&&Q>=0&&P<d&&Q<y){
A[P][Q]=14}}}};
this.linha=function(Q,S,P,R,T){
I[T].beginPath();
I[T].lineWidth=L;
I[T].moveTo(Q,S);
I[T].lineTo(P,R);
I[T].stroke();
I[T].closePath()};
this.gLinha=function(Q,X,P,V){
var Y;
var T=Math.abs(V-X)>Math.abs(P-Q);
if(T){
Y=Q;
Q=X;
X=Y;
Y=P;
P=V;
V=Y
}
if(Q>P){
Y=Q;
Q=P;
P=Y;
Y=X;
X=V;
V=Y
}
var aa=P-Q;
var Z=Math.abs(V-X);
var S=-aa/2;
var R;
var U=X;
if(X<V){
R=1
}else{
R=-1
}
for(var W=Q;W<=P;W++){
if(T){
j.gElipseF(U,W,Math.floor(L/2),Math.floor(L/2))
}else{
j.gElipseF(W,U,Math.floor(L/2),Math.floor(L/2))
}
S=S+Z;
if(S>=0){
U=U+R;
S=S-aa
}}};
this.retanguloF=function(P,R,Q,T,S){
I[S].fillRect(P,R,Q,T)
};
this.gRetanguloF=function(S,V,U,W){
var R=S+U;
var T=V+W;
v+="#"+S+"#"+V+"#"+U+"#"+W;
for(var P=S;P<R;P++){
for(var Q=V;Q<T;Q++){
A[P][Q]=g
}
}};
this.retanguloB=function(P,R,Q,T,S){
I[S].strokeRect(P,R,Q,T)
};
this.gRetanguloB=function(Q,U,X,S){
var R=Math.ceil(L/2);
var P=Q+X+R-1;
var T=U+S+R-1;
Q-=R;U-=R;
for(var Y=Q;Y<=P;Y++){
for(var W=0;W<L;W++){
A[Y][U+W]=g;A[Y][T-W]=g}}
for(var V=U;V<=T;V++){
for(var W=0;W<L;W++){
A[Q+W][V]=g;A[P-W][V]=g}}};
this.elipseF=function(P,U,T,R,S){
if(o){I[S].fillEllipse(P-T,U-R,T*2,R*2)
}else{
var Q=4*((Math.sqrt(2)-1)/3);
I[S].beginPath();
I[S].moveTo(P,U-R);
I[S].bezierCurveTo(P+(Q*T),U-R,P+T,U-(Q*R),P+T,U);
I[S].bezierCurveTo(P+T,U+(Q*R),P+(Q*T),U+R,P,U+R);
I[S].bezierCurveTo(P-(Q*T),U+R,P-T,U+(Q*R),P-T,U);
I[S].bezierCurveTo(P-T,U-(Q*R),P-(Q*T),U-R,P,U-R);
I[S].fill()}};
this.gElipseF=function(U,T,X,aa){
if(X>0&&aa>0){
var Z;
var Y;
var ab,W;
var S;
var V,Q;
var R,P;
V=2*X*X;Q=2*aa*aa;
Z=X;
Y=0;
ab=aa*aa*(1-2*X);
W=X*X;
S=0;
R=Q*X;
P=0;
while(R>=P){
for(var ac=Z;ac>=1;ac--){
A[U+ac-1][T+Y-1]=g;A[U-ac][T+Y-1]=g;A[U-ac][T-Y]=g;A[U+ac-1][T-Y]=g}
Y++;
P+=V;
S+=W;
W+=V;
if((2*S+ab)>0){
Z--;
R-=Q;
S+=ab;
ab+=Q}}
Z=0;
Y=aa;
ab=aa*aa;W=X*X*(1-2*aa);
S=0;
R=0;
P=V*aa;
while(R<=P){
for(var ac=Z;ac>=1;ac--){
A[U+ac-1][T+Y-1]=g;
A[U-ac][T+Y-1]=g;
A[U-ac][T-Y]=g;
A[U+ac-1][T-Y]=g}
Z++;
R+=Q;
S+=ab;
ab+=Q;
if((2*S+W)>0){
Y--;
P-=V;
S+=W;
W+=V}}}};
this.elipseB=function(P,U,T,R,S){
var Q=4*((Math.sqrt(2)-1)/3);I[S].beginPath();I[S].moveTo(P,U-R);I[S].bezierCurveTo(P+(Q*T),U-R,P+T,U-(Q*R),P+T,U);I[S].bezierCurveTo(P+T,U+(Q*R),P+(Q*T),U+R,P,U+R);I[S].bezierCurveTo(P-(Q*T),U+R,P-T,U+(Q*R),P-T,U);I[S].bezierCurveTo(P-T,U-(Q*R),P-(Q*T),U-R,P,U-R);I[S].stroke()};this.gElipseB=function(U,T,X,aa){if(X>0&&aa>0){var Z;var Y;var ab,W;var S;var V,Q;var R,P;V=2*X*X;Q=2*aa*aa;Z=X;Y=0;ab=aa*aa*(1-2*X);W=X*X;S=0;R=Q*X;P=0;while(R>P){j.gElipseF(U+Z,T+Y,Math.floor(L/2),Math.floor(L/2));j.gElipseF(U-Z,T+Y,Math.floor(L/2),Math.floor(L/2));j.gElipseF(U-Z,T-Y,Math.floor(L/2),Math.floor(L/2));j.gElipseF(U+Z,T-Y,Math.floor(L/2),Math.floor(L/2));Y++;P+=V;S+=W;W+=V;if((2*S+ab)>0){Z--;R-=Q;S+=ab;ab+=Q}}Z=0;Y=aa;ab=aa*aa;W=X*X*(1-2*aa);S=0;R=0;P=V*aa;while(R<=P){j.gElipseF(U+Z,T+Y,Math.floor(L/2),Math.floor(L/2));j.gElipseF(U-Z,T+Y,Math.floor(L/2),Math.floor(L/2));j.gElipseF(U-Z,T-Y,Math.floor(L/2),Math.floor(L/2));j.gElipseF(U+Z,T-Y,Math.floor(L/2),Math.floor(L/2));Z++;R+=Q;S+=ab;ab+=Q;if((2*S+W)>0){Y--;P-=V;S+=W;W+=V}}}};function F(R,S,T){var U,W;U=S;W=T;if(A[S][T]==R){while(A[U+1][W]==R){U++}var Q=U;do{U=S-1;W++;while(A[U+1][W]==R&&U+1<=Q){U++}}while(U==Q);W--;var P=Q-S;var V=W-T;return{x:S,y:T,w:P,h:V}}else{return{w:-1,h:-1}}}function m(R,S,T){var U,W;U=S;W=T;if(A[S][T]==R){while(A[U-1][W]==R){U--}var Q=U;do{U=S+1;W--;while(A[U-1][W]==R&&U-1>=Q){U--}}while(U==Q);W++;var P=S-Q;var V=T-W;return{x:Q,y:W,w:P,h:V}}else{return{w:-1,h:-1}}}function D(Q,R,S){var T,W;T=R;W=S;if(A[R][S]==Q){while(A[T][W+1]==Q){W++}var V=W;do{W=S-1;T--;while(A[T][W+1]==Q&&W+1<=V){W++}}while(W==V);T++;var P=R-T;var U=V-S;return{x:T,y:S,w:P,h:U}}else{return{w:-1,h:-1}}}function n(Q,R,S){var T,W;T=R;W=S;if(A[R][S]==Q){while(A[T][W-1]==Q){W--}var V=W;do{W=S+1;T++;while(A[T][W-1]==Q&&W-1>=V){W--}}while(W==V);T--;var P=T-R;var U=S-V;return{x:R,y:V,w:P,h:U}}else{return{w:-1,h:-1}}}function x(P,U,R,S,T,Q){this.x=P;this.y=U;this.w=R;this.h=S;this.ref=T;this.andada=Q}this.balde=function(U,T){var P=A[U][T];if(g!=P){while(A[U-1][T]==P){U--}while(A[U][T-1]==P){T--}var X=F(P,U,T);var S=new x(U,T,X.w,X.h,0,0);var R;var W=X.w;var Q;v="";i.fillRect(X.x,X.y,X.w+1,X.h+1);j.gRetanguloF(X.x,X.y,X.w+1,X.h+1);v=v.substr(1,v.length);do{R=0;if(S.ref==2){R+=S.andada}while(R<=S.h){X=n(P,S.x+S.w+1,S.y+S.h+1-R);Q=X.h;if(Q!=-1){O[Q].push(new x(X.x,X.y,X.w,X.h,1,S.h+1-R));i.fillRect(X.x,X.y,X.w+1,X.h+1);j.gRetanguloF(X.x,X.y,X.w+1,X.h+1);if(Q>W){W=Q}R+=Q}else{R++}}R=0;if(S.ref==1){R+=S.andada}while(R<=S.h){X=D(P,S.x-1,S.y+R);Q=X.h;if(Q!=-1){O[Q].push(new x(X.x,X.y,X.w,X.h,2,S.h+1-R));i.fillRect(X.x,X.y,X.w+1,X.h+1);j.gRetanguloF(X.x,X.y,X.w+1,X.h+1);if(Q>W){W=Q}R+=Q}else{R++}}R=0;if(S.ref==4){R+=S.andada}while(R<=S.w){X=F(P,S.x+R,S.y+S.h+1);Q=X.w;if(Q!=-1){O[Q].push(new x(X.x,X.y,X.w,X.h,3,S.w+1-R));i.fillRect(X.x,X.y,X.w+1,X.h+1);j.gRetanguloF(X.x,X.y,X.w+1,X.h+1);if(Q>W){W=Q}R+=Q}else{R++}}R=0;if(S.ref==3){R+=S.andada}while(R<=S.w){X=m(P,S.x+S.w+1-R,S.y-1);Q=X.w;if(Q!=-1){O[Q].push(new x(X.x,X.y,X.w,X.h,4,S.w+1-R));i.fillRect(X.x,X.y,X.w+1,X.h+1);j.gRetanguloF(X.x,X.y,X.w+1,X.h+1);if(Q>W){W=Q}R+=Q}else{R++}}S=O[W].pop();while(S==null&&W>0){S=O[--W].pop()}}while(S!=null);var V="3@"+v;h.push(V);if(j.onCodigo instanceof Function){j.onCodigo(V)}}};function k(P){if(!N&&q){document.onmousemove=c;document.onmouseup=u;c(P);N=true;M=b;s=z;switch(H){case 0:B();v=M+"#"+s;break;case 7:B();j.balde(M,s);N=false;break;case 8:j.mudaCor(A[M][s],true);if(j.onSelCor instanceof Function){j.onSelCor()}N=false;break;case 1:B();v=M+"#"+s;j.borracha(b,z,0,true);break}if(j.onStartDraw instanceof Function){j.onStartDraw()}}}function r(S){var R,Q,T,P;Q=T=0;if(S.getBoundingClientRect){R=S.getBoundingClientRect();Q=Math.round(R.left);T=Math.round(R.top);Q+=document.documentElement.scrollLeft;T+=document.documentElement.scrollTop}else{if(document.getBoxObjectFor){R=document.getBoxObjectFor(S);Q=R.x;T=R.y}else{while(S&&S.nodeName!="BODY"){Q+=S.offsetLeft;T+=S.offsetTop;if(window.opera){Q-=1;T-=1}else{Q+=1;T+=1}P=parseInt(document.defaultView.getComputedStyle(S,null).getPropertyValue("border-width"));if(P>0){Q+=P;T+=P}S=S.offsetParent}}}return{x:Q,y:T}}function c(Q){if(o){b=event.clientX;z=event.clientY}else{if(document.layers){b=Q.x;z=Q.y}else{if(C){b=Q.clientX;z=Q.clientY}}}b=b+document.documentElement.scrollLeft-r(l).x;z=z+document.documentElement.scrollTop-r(l).y;var P=Math.floor(L/2);if(b>=d-P){b=d-P}else{if(b<=P){b=P}}if(z>=y-P){z=y-P}else{if(z<=P){z=P}}if(N&&q){if(H==0){if(v.length>230){var R="2@"+v;h.push(R);if(j.onCodigo instanceof Function){j.onCodigo(R)}v=M+"#"+s}j.linha(M,s,b,z,0);j.gLinha(M,s,b,z);M=b;s=z;v+="#"+M+"#"+s}else{if(H==1){if(v.length>230){var R="21@"+v;h.push(R);if(j.onCodigo instanceof Function){j.onCodigo(R)}v=b+"#"+z}j.borracha(b,z,0,true);v+="#"+b+"#"+z}else{K.clearRect(0,0,d,y);j.desenhar(M,s,b,z,H,1,false)}}}if(o){event.cancelBubble=true;event.returnValue=false;event.stopped=true}else{Q.preventDefault();Q.stopPropagation()}}this.desenhar=function(S,ad,Q,aa,X,ac,V){if(S==Q&&ad==aa){return}var ab,Z,Y,W;if(S>=Q){ab=Q;Z=S}else{ab=S;Z=Q}if(ad>=aa){Y=aa;W=ad}else{Y=ad;W=aa}if(V){B()}switch(X){case 6:j.linha(S,ad,Q,aa,ac);if(V){j.gLinha(S,ad,Q,aa)}break;case 2:if(ab==Z||Y==W){V=false;break}j.retanguloF(ab,Y,Z-ab,W-Y,ac);if(V){j.gRetanguloF(ab,Y,Z-ab,W-Y)}break;case 3:j.retanguloB(ab,Y,Z-ab,W-Y,ac);if(V){j.gRetanguloB(ab,Y,Z-ab,W-Y)}break;case 4:if(ab==Z||Y==W){V=false;break}var R=Math.floor((Z-ab)/2);var P=Math.floor((W-Y)/2);var U=Math.round(ab+R);var T=Math.round(Y+P);j.elipseF(U,T,R,P,ac);if(V){j.gElipseF(U,T,R,P)}break;case 5:var R=Math.floor((Z-ab)/2);var P=Math.floor((W-Y)/2);var U=Math.round(ab+R);var T=Math.round(Y+P);j.elipseB(U,T,R,P,ac);if(V){j.gElipseB(U,T,R,P)}break}if(V){var ae="1@"+X+"#"+S+"#"+ad+"#"+Q+"#"+aa;h.push(ae);if(j.onCodigo instanceof Function){j.onCodigo(ae)}}};function u(P){if(N&&q){var Q;if(H==0){Q="2@"+v;h.push(Q);if(j.onCodigo instanceof Function){j.onCodigo(Q)}}else{if(H==1){Q="21@"+v;h.push(Q);if(j.onCodigo instanceof Function){j.onCodigo(Q)}}else{j.desenhar(M,s,b,z,H,0,true);K.clearRect(0,0,d,y)}}N=false}}this.mudaOpcao=function(P){if(P==1){i.fillStyle="#FFFFFF"}else{if(H==1){i.fillStyle=f[g]}}H=P};this.mudaBorda=function(P,R){i.lineWidth=P;if(l!=null){K.lineWidth=P}L=P;if(R){var Q="6@"+P;h.push(Q);if(j.onCodigo instanceof Function){j.onCodigo(Q)}}};this.mudaCor=function(P,R){P=parseInt(P);i.strokeStyle=f[P];if(l!=null){K.strokeStyle=f[P];K.fillStyle=f[P]}if(H!=1){i.fillStyle=f[P]}g=P;if(R){var Q="5@"+P;h.push(Q);if(j.onCodigo instanceof Function){j.onCodigo(Q)}}};this.limparTela=function(R,S){if(R){A=new Array(d);for(var P=0;P<d;P++){A[P]=new Array(y);for(var Q=0;Q<y;Q++){A[P][Q]=14}}A[-1]=new Array();A[d]=new Array();for(var P=-1;P<=y;P++){A[-1][P]=0;A[d][P]=0}for(P=0;P<d;P++){A[P][-1]=0;A[P][y]=0}h=["5@"+g,"6@"+L];if(!S){if(j.onCodigo instanceof Function){j.onCodigo("4@")}}}i.clearRect(0,0,d,y);i.fillStyle="#FFF";j.retanguloF(0,0,d,y,0);i.fillStyle=f[g];if(l!=null){K.clearRect(0,0,d,y)}N=false;if(R){B()}};this.desfazer=function(){if(J){if(o){i.undo()}else{i.drawImage(a,0,0)}for(var P=0;P<d;P++){for(var Q=0;Q<y;Q++){A[P][Q]=w[P][Q]}}h=t.split("|")}};function B(){if(J){if(o){i.saveState()}else{G.clearRect(0,0,d,y);G.fillStyle="#FFF";j.retanguloF(0,0,d,y,2);G.drawImage(e,0,0)}w=new Array(d);for(var P=0;P<d;P++){w[P]=new Array(y);for(var Q=0;Q<y;Q++){w[P][Q]=A[P][Q]}}t=h.join("|")}}this.salvarPNG=function(){if(e.toDataURL()){return e.toDataURL()}else{return false}};this.salvarJPG=function(){if(e.toDataURL("image/jpeg")&&!window.opera){return e.toDataURL("image/jpeg")}else{return false}};this.getSequencia=function(){return h.join("|")};this.setSequencia=function(P){h=P.split("|")};this.getCor=function(){return g};this.getCorReal=function(){return f[g]};this.getLargura=function(){return d};this.getAltura=function(){return y};this.getUltimaAcao=function(){if(h.length>0){return h[h.length-1]}return null};this.liberar=function(P){q=P;if(P){e.style.cursor="crosshair";if(l!=null){l.style.cursor="crosshair"}}else{e.style.cursor="default";if(l!=null){l.style.cursor="default"}}};if(l!=null){K=l.getContext("2d");I.push(K);l.onmousedown=k;if(document.all&&!window.opera){e.onmousedown=k;
e.onselectstart=function(P){k(P);return false};
l.onselectstart=function(P){k(P);return false}}
K.lineCap="round";K.fillStyle="#000000";K.strokeStyle="#000000";K.lineWidth=L}
i.lineCap="round";i.fillStyle="#000000";i.strokeStyle="#000000";i.lineWidth=L;
if(J&&!o){a=document.createElement("canvas");a.width=d;a.height=y;a.style.display="none";document.body.appendChild(a);
G=a.getContext("2d");
I.push(G);
G.lineCap="round";
G.fillStyle="#000000";G.strokeStyle="#000000";G.lineWidth=L}
for(var E=0;E<=d;E++){O[E]=new Array()}j.limparTela(true)}
