function springDataTablesTranslator(url, columns, param) {
    return function (data, callback, settings) {
        let sort = data.order.length === 0 ? null :
            columns[data.order[0].column].data // sort column name
            + ',' + data.order[0].dir; // sort direction
        $.post(url, {
            page: data.start / data.length,
            size: data.length,
            sort: sort
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