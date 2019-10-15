/*
 * @Author: YP
 * @Date:   2018-03-10 09:18:20
 * @Last Modified by:   YP
 * @Last Modified time: 2018-03-13 09:22:14
 */
//import add from './test'  ///假设里面有一个add的函数,,,es6的语法
import '../css/all.css'
var Web3 = require('web3');
var web3;
if (typeof web3 !== 'undefined') 
{
    web3 = new Web3(web3.currentProvider);

   
      console.log("by chrome");
   
} else {
    // set the provider you want from Web3.providers

    web3 = new Web3(new Web3.providers.HttpProvider("http://127.0.0.1:8545"));

        console.log("by 8545");
    
}

function newUser()
{
	 alert("be called");

      var addressPromise=web3.personal.newAccount('yapie');
      console.log(typeof(addressPromise));
      console.log("addressPromise is" +addressPromise);
     // var address=web3.eth.personal.ecRecover("yapie",encrytAddress)
}
function importAccountByPrivateKey()
{
	console.log("import");
	//keyJson是私匙
	web3.personal.importRawKey("f987e76f9298a721896d560ab3a1a9717e7ef9874bfdbb693c3cd3e5b54f1d0e",'domore0325');
}
// function importAccountByJson()
// {
// 	//暂时放着
// }
function trasaction()   //调用固定地址上的合约（非只读）
{
	 // var web3= new Web3(new Web3.providers.HttpProvider("http://127.0.0.1:8545"));
  //    console.log("web3是"+web3);
	var abi=[ { "constant": true, "inputs": [], "name": "name", "outputs": [ { "name": "", "type": "string", "value": "bbbb" } ], "payable": false, "stateMutability": "view", "type": "function" }, { "constant": true, "inputs": [], "name": "totalSupply", "outputs": [ { "name": "", "type": "uint256", "value": "1e+22" } ], "payable": false, "stateMutability": "view", "type": "function" }, { "constant": true, "inputs": [], "name": "decimals", "outputs": [ { "name": "", "type": "uint8", "value": "18" } ], "payable": false, "stateMutability": "view", "type": "function" }, { "constant": true, "inputs": [ { "name": "", "type": "address" } ], "name": "balanceOf", "outputs": [ { "name": "", "type": "uint256", "value": "0" } ], "payable": false, "stateMutability": "view", "type": "function" }, { "constant": true, "inputs": [], "name": "symbol", "outputs": [ { "name": "", "type": "string", "value": "bbbbb" } ], "payable": false, "stateMutability": "view", "type": "function" }, { "constant": false, "inputs": [ { "name": "_to", "type": "address" }, { "name": "_value", "type": "uint256" } ], "name": "transfer", "outputs": [], "payable": false, "stateMutability": "nonpayable", "type": "function" }, { "inputs": [ { "name": "initialSupply", "type": "uint256", "index": 0, "typeShort": "uint", "bits": "256", "displayName": "initial Supply", "template": "elements_input_uint", "value": "10000" }, { "name": "tokenName", "type": "string", "index": 1, "typeShort": "string", "bits": "", "displayName": "token Name", "template": "elements_input_string", "value": "bbbb" }, { "name": "tokenSymbol", "type": "string", "index": 2, "typeShort": "string", "bits": "", "displayName": "token Symbol", "template": "elements_input_string", "value": "bbbbb" } ], "payable": false, "stateMutability": "nonpayable", "type": "constructor" } ];
	var contractAddress="0x9bAc440C16364d90678A4511ea1443820feAd113";
	//console.log(web3.eth.contract());
	var contract=window.web3.eth.contract(abi).at(contractAddress);
	console.log("contract is"+contract);
	var addressTo="0xf7de0c841b7b68fb63D80c730139634244838B71";
	var fromAccount="0xcc82bb1e4607db773a02fd35c42eb229b3f46fa3";//coinbase
	var unlockPromise=web3.personal.unlockAccount(fromAccount,"123456",10000);
	unlockPromise.then(function(data){
		console.log("unlock with success"+data);
	},function(error){
		console.log(error);
	}).then(function(){
		contract.transfer.sendTransaction(addressTo,10000,{from:fromAccount})
	}).then(function(data){
			console.log("trasaction sent!"+"   data is "+data);
	},function(error){
		console.log("trasaction failed"+error);
	});

 }