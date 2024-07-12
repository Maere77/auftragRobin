'use client'

import {useEffect, useState} from "react";
import "./player.css";
import Link from "next/link";

export default function Groups() {

    const [players, setPlayers] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const playersPerPage = 30;

    useEffect(() => {
        fetch("http://localhost:8080/api/groups")
            .then((response) => response.json())
            .then((data) => {
                setPlayers(data);
            })
            .catch((error) => console.error("Fehler: ", error));
    }, []);

    const indexOfLastPlayer = currentPage * playersPerPage;
    const indexOfFirstPlayer = indexOfLastPlayer - playersPerPage;
    const currentPlayers = players.slice(indexOfFirstPlayer, indexOfLastPlayer);

    const totalPages = Math.ceil(players.length / playersPerPage);


    return (
        <>
            <div className="container">
                <Link href={"./"} className="backLink">Home page</Link>
                <table className={"table"}>
                    <thead>
                    <tr className={"headerRow"}>
                        <th>ID</th>
                        <th>Name</th>
                        <th></th>
                        <th></th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    {currentPlayers.map((player, index) => (
                        <tr key={player.id} className={index % 2 === 0 ? "evenRow" : "oddRow"}>
                            <td>{player.id}</td>
                            <td>{player.groupName}</td>
                            <td>
                                <a href={`./player/edit/${player.id}`}>EDIT</a>
                            </td>
                            <td>
                                <a className={"ef"} href={`./player/edit/${player.id}`}>DELETE</a>
                            </td>
                            <td>
                                <a className={"ef"} href={`./player/edit/${player.id}`}>SHOW</a>
                            </td>
                        </tr>
                    ))}
                    </tbody>
                </table>
                <div className={"pagination"}>
                    <button
                        onClick={() => setCurrentPage(currentPage - 1)}
                        disabled={currentPage === 1}
                    >
                        Previous
                    </button>
                    {Array.from({length: totalPages}, (_, i) => (
                        <button
                            key={i + 1}
                            onClick={() => setCurrentPage(i + 1)}
                            className={currentPage === i + 1 ? "activePage" : ''}
                        >
                            {i + 1}
                        </button>
                    ))}
                    <button
                        onClick={() => setCurrentPage(currentPage + 1)}
                        disabled={currentPage === totalPages}
                    >
                        Next
                    </button>
                </div>
            </div>
        </>)
}