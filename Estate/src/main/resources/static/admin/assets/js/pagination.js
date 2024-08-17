$(document).ready(function() {
    function updatePageParam(pageNo) {
        const urlParams = new URLSearchParams(window.location.search);
        urlParams.set('pageNo', pageNo);
        window.location.search = urlParams.toString();
    }
    $('.pagination a').click(function(e) {
        e.preventDefault();
        const pageNo = $(this).data('page');
        updatePageParam(pageNo);
    });
});