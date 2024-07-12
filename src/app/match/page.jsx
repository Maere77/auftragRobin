'use client'

import { useEffect, useState } from "react";
import "./player.css";
import Link from "next/link";

export default function Match() {
    const [group, setGroup] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const playersPerPage = 30;

    useEffect(() => {
        fetch("http://localhost:8080/api/matches")
            .then((response) => response.json())
            .then((data) => {
                setGroup(data);
            })
            .catch((error) => console.error("Fehler: ", error));
    }, []);

    const indexOfLastPlayer = currentPage * playersPerPage;
    const indexOfFirstPlayer = indexOfLastPlayer - playersPerPage;
    const currentGroup = group.slice(indexOfFirstPlayer, indexOfLastPlayer);

    const totalPages = Math.ceil(group.length / playersPerPage);

    return (
        <div className="container">
            <Link href={"./"} className="backLink">Home page</Link>
            <table className="table">
                <thead>
                <tr className="headerRow">
                    <th>ID</th>
                    <th>Winner</th>
                    <th></th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                {currentGroup.map((group, index) => (
                    <tr key={group.id} className={index % 2 === 0 ? "evenRow" : "oddRow"}>
                        <td>{group.id}</td>
                        <td>{group.winner}</td>
                        <td>
                            <a href={`./match/edit/${group.id}`} className="actionLink">EDIT</a>
                        </td>
                        <td>
                            <a href={`./match/edit/${group.id}`} className="actionLink">DELETE</a>
                        </td>
                        <td>
                            <a href={`./match/edit/${group.id}`} className="actionLink">SHOW</a>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
            <div className="pagination">
                <button
                    onClick={() => setCurrentPage(currentPage - 1)}
                    disabled={currentPage === 1}
                    className="pageButton"
                >
                    Previous
                </button>
                {Array.from({ length: totalPages }, (_, i) => (
                    <button
                        key={i + 1}
                        onClick={() => setCurrentPage(i + 1)}
                        className={`pageButton ${currentPage === i + 1 ? "activePage" : ""}`}
                    >
                        {i + 1}
                    </button>
                ))}
                <button
                    onClick={() => setCurrentPage(currentPage + 1)}
                    disabled={currentPage === totalPages}
                    className="pageButton"
                >
                    Next
                </button>
            </div>
        </div>
    );
}