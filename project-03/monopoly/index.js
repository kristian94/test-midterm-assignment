const req = (name) => require(`./${name}/${name}`);
req('board');
req('player');
req('square');
req('square-type');