function springDataTablesTranslator(url, columns, paramSupplier) {
    return function (data, callback, settings) {
        let sort = data.order.length === 0 ? null :
            columns[data.order[0].column].data // sort column name
            + ',' + data.order[0].dir; // sort direction
        let postData = {
            page: data.start / data.length,
            size: data.length,
            sort: sort
        };
        console.debug("page & sort", postData);
        let param = paramSupplier ? paramSupplier() : {};
        console.debug("search param", param);
        Object.assign(postData, param);
        $.post(url, postData, function(res) {
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