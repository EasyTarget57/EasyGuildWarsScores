var egwd;
function getEasyGuildWarsData(){
	if(!egwd){
		egwd = JSON.parse('$egwd');
	}
	return egwd;
}
