let _ = require('lodash');

function bookInOrder(books,Sort,search) {
    return (_.orderBy(books, Sort.sort_type,Sort.order))
        .filter(
            data=>!search ||
                data.cname.includes(search) ||
                data.writer.includes(search) ||
                data.isbn.includes(search));
}

export {
    bookInOrder
}