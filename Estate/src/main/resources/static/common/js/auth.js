// Hàm này sẽ tự động thêm header Authorization vào tất cả các request
(function(open) {
    XMLHttpRequest.prototype.open = function() {
        open.apply(this, arguments);
        const token = localStorage.getItem('accessToken');
        if (token) {
            this.setRequestHeader('Authorization', 'Bearer ' + token);
        }
    };
})(XMLHttpRequest.prototype.open);
