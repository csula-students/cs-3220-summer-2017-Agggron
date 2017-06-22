var dom = document.querySelector('.rgb-square');

var list = ['--red', '--blue', '--green'];
var counter = 0;

dom.addEventListener('click', function () {
	dom.classList.remove(list[counter - 1]);
	if (counter >= list.length) {
		counter = 0;
	}
	dom.classList.add(list[counter]);

	console.log(counter);
	console.log(dom.classList);
	counter = counter + 1;
	console.log("clicked");
});