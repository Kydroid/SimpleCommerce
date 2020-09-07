import React from "react";

export class CategoryMenu extends React.Component<any, any> {
    render() {
        return (
            <aside id="category-menu" className="col-lg-3">
                <h1 className="my-4">Shop Name</h1>
                <div className="list-group">
                    <a className="list-group-item">Category 1</a>
                    <a className="list-group-item">Category 2</a>
                    <a className="list-group-item">Category 3</a>
                </div>
            </aside>
        );
    }

}
