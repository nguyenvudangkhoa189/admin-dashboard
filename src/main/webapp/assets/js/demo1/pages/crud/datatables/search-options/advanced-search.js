"use strict";
var KTDatatablesSearchOptionsAdvancedSearch = function() {

	$.fn.dataTable.Api.register('column().title()', function() {
		return $(this.header()).text().trim();
	});

	var initTable1 = function() {
		// begin first table
		let columns = [
			{data: 'recordID'},
			{data: 'orderID'},
			{data: 'country'},
			{data: 'shipCity'},
			{data: 'companyAgent'},
			{data: 'shipDate'},
			{data: 'status'},
			{data: 'type'},
			{data: 'Actions', responsivePriority: -1},
		];
		var table = $('#kt_table_1').DataTable({
			responsive: true,
			// Pagination settings
			dom: `<'row'<'col-sm-12'tr>>
			<'row'<'col-sm-12 col-md-5'i><'col-sm-12 col-md-7 dataTables_pager'lp>>`,
			// read more: https://datatables.net/examples/basic_init/dom.html

			lengthMenu: [5, 10, 25, 50],

			pageLength: 10,

			language: {
				'lengthMenu': 'Display _MENU_',
			},

			searchDelay: 500,
			processing: true,
			serverSide: true,
			ajax: springDataTablesTranslator(document.baseURI + 'order/search', columns, getSearchParam),
			columns: columns,

			initComplete: function() {
				this.api().columns().every(function() {
					var column = this;

					switch (column.title()) {
						case 'Country':
							column.data().unique().sort().each(function(d, j) {
								$('.kt-input[data-col-index="2"]').append('<option value="' + d + '">' + d + '</option>');
							});
							break;

						case 'Status':
							var status = {
								'Pending': {'title': 'Pending', 'class': 'kt-badge--brand'},
								'Delivered': {'title': 'Delivered', 'class': ' kt-badge--danger'},
								'Canceled': {'title': 'Canceled', 'class': ' kt-badge--primary'},
								'Success': {'title': 'Success', 'class': ' kt-badge--success'},
								'Info': {'title': 'Info', 'class': ' kt-badge--info'},
								'Danger': {'title': 'Danger', 'class': ' kt-badge--danger'},
								'Warning': {'title': 'Warning', 'class': ' kt-badge--warning'},
							};
							column.data().unique().sort().each(function(d, j) {
								console.debug('statud[d]', d);
								$('.kt-input[data-col-index="6"]').append('<option value="' + d + '">' + status[d].title + '</option>');
							});
							break;

						case 'Type':
							var status = {
								'Online': {'title': 'Online', 'state': 'danger'},
								'Retail': {'title': 'Retail', 'state': 'primary'},
								'Direct': {'title': 'Direct', 'state': 'success'},
							};
							column.data().unique().sort().each(function(d, j) {
								$('.kt-input[data-col-index="7"]').append('<option value="' + d + '">' + status[d].title + '</option>');
							});
							break;
					}
				});
			},

			columnDefs: [
				{
					targets: -1,
					title: 'Actions',
					orderable: false,
					render: function(data, type, full, meta) {
						return `
                        <span class="dropdown">
                            <a href="#" class="btn btn-sm btn-clean btn-icon btn-icon-md" data-toggle="dropdown" aria-expanded="true">
                              <i class="la la-ellipsis-h"></i>
                            </a>
                            <div class="dropdown-menu dropdown-menu-right">
                                <a class="dropdown-item" href="#"><i class="la la-edit"></i> Edit Details</a>
                                <a class="dropdown-item" href="#"><i class="la la-leaf"></i> Update Status</a>
                                <a class="dropdown-item" href="#"><i class="la la-print"></i> Generate Report</a>
                            </div>
                        </span>
                        <a href="#" class="btn btn-sm btn-clean btn-icon btn-icon-md" title="View">
                          <i class="la la-edit"></i>
                        </a>`;
					},
				},
				{
					targets: 6,
					render: function(data, type, full, meta) {
						var status = {
							'Pending': {'title': 'Pending', 'class': 'kt-badge--brand'},
							'Delivered': {'title': 'Delivered', 'class': ' kt-badge--danger'},
							'Canceled': {'title': 'Canceled', 'class': ' kt-badge--primary'},
							'Success': {'title': 'Success', 'class': ' kt-badge--success'},
							'Info': {'title': 'Info', 'class': ' kt-badge--info'},
							'Danger': {'title': 'Danger', 'class': ' kt-badge--danger'},
							'Warning': {'title': 'Warning', 'class': ' kt-badge--warning'},
						};
						if (typeof status[data] === 'undefined') {
							return data;
						}
						return '<span class="kt-badge ' + status[data].class + ' kt-badge--inline kt-badge--pill">' + status[data].title + '</span>';
					},
				},
				{
					targets: 7,
					render: function(data, type, full, meta) {
						var status = {
							'Online': {'title': 'Online', 'state': 'danger'},
							'Retail': {'title': 'Retail', 'state': 'primary'},
							'Direct': {'title': 'Direct', 'state': 'success'},
						};
						if (typeof status[data] === 'undefined') {
							return data;
						}
						return '<span class="kt-badge kt-badge--' + status[data].state + ' kt-badge--dot"></span>&nbsp;' +
							'<span class="kt-font-bold kt-font-' + status[data].state + '">' + status[data].title + '</span>';
					},
				}
			],
		});

		function getSearchParam() {
			let country = $('#in-country').val();
			let agent = $('#in-agent').val();
			let shipDateFrom = $('#in-shipDate-from').datepicker('getUTCDate');
			shipDateFrom = shipDateFrom ? shipDateFrom.getTime() : null;
			let shipDateTo = $('#in-shipDate-to').datepicker('getUTCDate');
			shipDateTo = shipDateTo ? shipDateTo.getTime() : null;
			return {country, agent, shipDateFrom, shipDateTo};
		}

		var filter = function() {
			var val = $.fn.dataTable.util.escapeRegex($(this).val());
			table.column($(this).data('col-index')).search(val ? val : '', false, false).draw();
		};

		var asdasd = function(value, index) {
			var val = $.fn.dataTable.util.escapeRegex(value);
			table.column(index).search(val ? val : '', false, true);
		};

		$('#kt_search').on('click', function(e) {
			e.preventDefault();
			var params = {};
			$('.kt-input').each(function() {
				var i = $(this).data('col-index');
				if (params[i]) {
					params[i] += '|' + $(this).val();
				}
				else {
					params[i] = $(this).val();
				}
			});
			$.each(params, function(i, val) {
				// apply search params to datatable
				table.column(i).search(val ? val : '', false, false);
			});
			table.table().draw();
		});

		$('#kt_reset').on('click', function(e) {
			e.preventDefault();
			$('.kt-input').each(function() {
				$(this).val('');
				table.column($(this).data('col-index')).search('', false, false);
			});
			table.table().draw();
		});

		$('#kt_datepicker').datepicker({
			todayHighlight: true,
			templates: {
				leftArrow: '<i class="la la-angle-left"></i>',
				rightArrow: '<i class="la la-angle-right"></i>',
			},
		});

	};

	return {

		//main function to initiate the module
		init: function() {
			initTable1();
		},

	};

}();

jQuery(document).ready(function() {
	KTDatatablesSearchOptionsAdvancedSearch.init();
});