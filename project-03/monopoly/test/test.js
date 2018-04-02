const req = (name) => require(`../${name}/test`);

req('board');
req('player');
req('square');
req('dice');
