function springDataTablesTranslator(url, param) {
    return function (data, callback, settings) {
        $.post(url, {
            page: data.start / data.length,
            size: data.length
        }, function(res) {
            // map your server's response to the DataTables format and pass it to
            // DataTables' callback
            callback({
                recordsTotal: res.totalElements,
                recordsFiltered: res.totalElements,
                data: res.content
            });
        });
    }
}