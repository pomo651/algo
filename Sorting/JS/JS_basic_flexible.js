// flexible & scalable rather than hard coding
// 尽量让代码可以复用，不受个别例子影响

// 题目var a = ["Wind", "Water", "Fire"];  如何打印出 "Wind, Water, Fire" ?

const a = ["Wind", "Water", "Fire"];
const delimiter = ", ";
console.log(a.join(delimiter));

// join 的基本用法
// The join() method creates and returns a new string. If an element is undefined, null or an empty array [], it is converted to an empty string.

const elements = ["Fire", "Air", "Water"];
console.log(elements.join());
// expected output: ‘Fire,Air,Water’

const a = ["Wind", "Water", "Fire"];
a.join(); // 'Wind,Water,Fire'
a.join(", "); // 'Wind, Water, Fire'
a.join(" + "); // 'Wind + Water + Fire'
a.join(""); // 'WindWaterFire'
