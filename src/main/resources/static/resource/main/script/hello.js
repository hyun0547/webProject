const hello = document.querySelector(".hello"),
  time = document.querySelector(".clock"),
  today = document.querySelector(".date");

const week = today.innerText.substring(today.innerText.lastIndexOf(" ") + 1);
const hour = parseInt(time.innerText.substring(0, 2));

fetch(`https://api.adviceslip.com/advice`)
	.then((Response) => Response.json())
	.then((data) => hello.innerText = data.slip.advice);
