var application;

var json1 = 
	{
		components:[
			{_type:"label",label:"Nome"},
			{_type:"textfield",name:"nome",id:"nome"},
			{_type:"label",label:"Sobrenome"},
			{_type:"textfield",name:"sobrenome",id:"sobrenome"},
			{_type:"label",label:"Nascimento"},
			{_type:"textfield",name:"nascimento",id:"nascimento"}
		]
	};

function start() {
	application = new Application();	
}
