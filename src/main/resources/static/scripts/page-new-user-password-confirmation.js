let pswd = getElementById('pswd')
let pswd2 = getElementById('pswdconf')
let buttonCreateAccount = document.getElementById('buttonCreateAccount')

pswd2.addEventListener('change', () ->{

if(pswd == pswd2){
    buttonCreateAccount.removeAttribute("disabled")
}

})
console.log(pswd)
console.log(pswd2)